package com.example.demo.token;

import com.example.demo.exception.SystemException;
import com.example.demo.model.Email;
import com.example.demo.model.ErrorMessage;
import com.example.demo.model.Password;
import com.example.demo.model.Teacher;
import com.example.demo.repository.TeacherRepository;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Date;
import java.util.function.Supplier;

@Service
public class TokenManagerImpl implements TokenManager {
    @Autowired
    private TeacherRepository teacherRepository;
    private static final String SECRET = "";// 키는 DB에 넣어두고, 절대 변하지 않으므로, 어플리케이션 실행직후 이를 불러와 Bean으로 등록하여 사용할 것임.
    private static final String ISS = "Great";
    @Override
    public int getIdFromAccessToken(AccessToken token) {
        String tokenString = token.getTokenString();
        tokenString = tokenString.replace("Bearer ","");
        JwtParser parser = Jwts.parser();
        Claims claims = parser.setSigningKey(SECRET).parseClaimsJws(tokenString).getBody();
        if(validateToken(tokenString)){
            return claims.get("id",Integer.class);
        }else {
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
            return tokens;
        }else {
            if(!validateToken(refreshTokenString)){
                ErrorMessage errorMessage = ErrorMessage.builder()
                        .message("Refresh 토큰이 만료되었습니다. 재 로그인이 필요합니다.")
                        .code(401).build();
                throw new SystemException(errorMessage);
            }else {
                int idFromAccessToken = getIdFromAccessToken(accessToken);
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

    @Override
    public Tokens makeTokens(Email email, Password password) {
        Teacher teacher = teacherRepository.findByEmailPassword(email,password).orElseThrow((Supplier<SystemException>) () -> {
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
        Claims claims = parser.setSigningKey(SECRET).parseClaimsJws(tokenString).getBody();
        String iss = claims.getIssuer();
        Date exp = claims.getExpiration();
        Date now = new Date();
        return iss.contentEquals(ISS) && now.before(exp);
    }
    private int getIdFromRefreshToken(RefreshToken token) {
        String tokenString = token.getTokenString();
        tokenString = tokenString.replace("Bearer ","");
        JwtParser parser = Jwts.parser();
        Claims claims = parser.setSigningKey(SECRET).parseClaimsJws(tokenString).getBody();
        if(validateToken(tokenString)){
            return claims.get("id",Integer.class);
        }else {
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
