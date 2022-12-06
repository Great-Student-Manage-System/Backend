package com.great.token;

import com.great.exception.SystemException;
import com.great.model.Email;
import com.great.model.Password;
import com.great.model.dto.response.ErrorMessage;
import com.great.model.dto.response.SelectTeacherResponseDto;
import com.great.repository.TeacherRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.FixedClock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.function.Supplier;

@Service
public class TokenManagerImpl implements TokenManager {
    @Autowired
    private TeacherRepository teacherRepository;
    @Value("${jwt.key}")
    private String SECRET;
    private static final String ISS = "Great";
    @Override
    public int getIdFromAccessToken(AccessToken token) {
        String tokenString = token.getTokenString();
        tokenString = tokenString.replace("Bearer ","");
        JwtParser parser = Jwts.parser();
        io.jsonwebtoken.Clock fixed = new FixedClock(Date.from(Instant.parse("1999-04-29T10:15:30.00Z")));
        Claims claims = parser.setClock(fixed).setSigningKey(SECRET).parseClaimsJws(tokenString).getBody();
        if (validateToken(tokenString)) {
            return claims.get("id", Integer.class);
        } else {
            ErrorMessage errorMessage = ErrorMessage.builder()
                    .message("인증토큰이 만료되었습니다.")
                    .code(401).build();
            throw new SystemException(errorMessage);
        }
    }

    @Override
    public Tokens reMakeTokens(Tokens tokens) {
        AccessToken accessToken = tokens.getAccessToken();
        RefreshToken refreshToken = tokens.getRefreshToken();
        String accessTokenString = accessToken.getTokenString();
        String refreshTokenString = refreshToken.getTokenString();
        if(validateToken(accessTokenString)&&validateToken(refreshTokenString)){
            ErrorMessage errorMessage = ErrorMessage.builder()
                    .message("아직 엑세스 토큰이 유효합니다. 갱신되지 않았습니다.")
                    .code(400).build();
            throw new SystemException(errorMessage);
        }else {
            if(!validateToken(refreshTokenString)){
                ErrorMessage errorMessage = ErrorMessage.builder()
                        .message("Refresh 토큰이 만료되었습니다. 재 로그인이 필요합니다.")
                        .code(401).build();
                throw new SystemException(errorMessage);
            }else {
                int idFromAccessToken = getIdFromAccessTokenWithoutValidate(accessToken);
                int idFromRefreshToken = getIdFromRefreshToken(refreshToken);
                if(idFromAccessToken==idFromRefreshToken){
                    AccessToken remadeAccessToken = new AccessToken(makeAccessTokenStringById(idFromAccessToken));
                    RefreshToken remadeRefreshToken = new RefreshToken(makeRefreshTokenStringById(idFromAccessToken));
                    return new Tokens(remadeAccessToken,remadeRefreshToken);
                }else{ // 엑세스토큰이나 리프레쉬 토큰이 잘못된 경우
                    ErrorMessage errorMessage = ErrorMessage.builder()
                            .message("토큰이 유효하지 않습니다. 재 로그인이 필요합니다.")
                            .code(401).build();
                    throw new SystemException(errorMessage);
                }
            }
        }
    }
    private int getIdFromAccessTokenWithoutValidate(AccessToken token) {
        String tokenString = token.getTokenString();
        tokenString = tokenString.replace("Bearer ","");
        JwtParser parser = Jwts.parser();
        io.jsonwebtoken.Clock fixed = new FixedClock(Date.from(Instant.parse("1999-04-29T10:15:30.00Z")));
        Claims claims = parser.setClock(fixed).setSigningKey(SECRET).parseClaimsJws(tokenString).getBody();
        return claims.get("id", Integer.class);
    }

    @Override
    public Tokens makeTokens(Email email, Password password) {
        SelectTeacherResponseDto teacher = teacherRepository.findByEmailPassword(email,password).orElseThrow((Supplier<SystemException>) () -> {
            ErrorMessage errorMessage = ErrorMessage.builder()
                    .message("이메일 혹은 비밀번호가 잘못되었습니다.")
                    .code(401).build();
            throw new SystemException(errorMessage);
        });
        AccessToken accessToken = new AccessToken(makeAccessTokenStringById(teacher.getId()));
        RefreshToken refreshToken = new RefreshToken(makeRefreshTokenStringById(teacher.getId()));
        return new Tokens(accessToken,refreshToken);
    }
    private boolean validateToken(String tokenString){
        tokenString = tokenString.replace("Bearer ","");
        JwtParser parser = Jwts.parser();
        try {
            Claims claims = parser.setSigningKey(SECRET).parseClaimsJws(tokenString).getBody();
            String iss = claims.getIssuer();
            Date exp = claims.getExpiration();
            Date now = new Date();
            return iss.contentEquals(ISS) && now.before(exp);
        }catch (Exception e){
            return false;
        }
    }
    private int getIdFromRefreshToken(RefreshToken token) {
        String tokenString = token.getTokenString();
        tokenString = tokenString.replace("Bearer ","");
        JwtParser parser = Jwts.parser();
        try {
            Claims claims = parser.setSigningKey(SECRET).parseClaimsJws(tokenString).getBody();
            if (validateToken(tokenString)) {
                return claims.get("id", Integer.class);
            } else {
                ErrorMessage errorMessage = ErrorMessage.builder()
                        .message("토큰이 유효하지 않습니다. 재 로그인이 필요합니다.")
                        .code(401).build();
                throw new SystemException(errorMessage);
            }
        }catch (ExpiredJwtException e){
            ErrorMessage errorMessage = ErrorMessage.builder()
                    .message("토큰이 유효하지 않습니다. 재 로그인이 필요합니다.")
                    .code(401).build();
            throw new SystemException(errorMessage);
        }
    }
    private String makeAccessTokenStringById(int teacherId){
        Date now = new Date();
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setHeaderParam(Header.TYPE,Header.JWT_TYPE)
                .setIssuer(ISS)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime()+ Duration.ofMinutes(30).toMillis()))
                .claim("id",teacherId)
                .signWith(SignatureAlgorithm.HS256,SECRET);
        return "Bearer "+jwtBuilder.compact();
    }
    private String makeRefreshTokenStringById(int teacherId){
        Date now = new Date();
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setHeaderParam(Header.TYPE,Header.JWT_TYPE)
                .setIssuer("Great")
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime()+ Duration.ofDays(14).toMillis()))
                .claim("id",teacherId)
                .signWith(SignatureAlgorithm.HS256,SECRET);
        return "Bearer "+jwtBuilder.compact();
    }
}