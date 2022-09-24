package com.example.demo.token;

import com.example.demo.exception.SystemException;
import com.example.demo.model.Email;
import com.example.demo.model.ErrorMessage;
import com.example.demo.model.Password;
import com.example.demo.model.Teacher;
import com.example.demo.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public class TokenManagerImpl implements TokenManager {
    @Autowired
    private TeacherRepository teacherRepository;
    @Override
    public int getIdFromAccessToken(AccessToken token) {
        return token.getId();
    }

    @Override
    public Tokens reMakeTokens(Tokens tokens) {
        AccessToken accessToken = tokens.getAccessToken();
        RefreshToken refreshToken = tokens.getRefreshToken();
        if(accessToken.isValid()&&refreshToken.isValid()){
            return tokens;
        }else {
            if(!refreshToken.isValid()){
                ErrorMessage errorMessage = ErrorMessage.builder()
                        .message("Refresh 토큰이 만료되었습니다. 재 로그인이 필요합니다")
                        .code(401).build();
                throw new SystemException(errorMessage);
            }else {
                int idFromAccessToken = accessToken.getId();
                int idFromRefreshToken = refreshToken.getId();
                if(idFromAccessToken==idFromRefreshToken){
                    AccessToken remadeAccessToken = new AccessToken(idFromAccessToken);
                    RefreshToken remadeRefreshToken = new RefreshToken(idFromRefreshToken);
                    return new Tokens(remadeAccessToken,remadeRefreshToken);
                }else{ // 엑세스토큰이나 리프레쉬 토큰이 잘못된 경우
                    ErrorMessage errorMessage = ErrorMessage.builder()
                            .message("토큰이 손상되었습니다 재 로그인이 필요합니다")
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
        AccessToken accessToken = new AccessToken(teacher.getId());
        RefreshToken refreshToken = new RefreshToken(teacher.getId());
        return new Tokens(accessToken,refreshToken);
    }
}
