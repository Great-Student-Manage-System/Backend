package com.great.springboot.interceptor;

import com.great.springboot.exception.SystemException;
import com.great.model.dto.response.ErrorMessage;
import com.great.application.service.token.AccessToken;
import com.great.application.port.in.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthInterceptor implements HandlerInterceptor {
    @Autowired
    private TokenManager tokenManager;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if(HttpMethod.OPTIONS.matches(request.getMethod())){
            return true;
        }else{
            String accessToken = request.getHeader("Authorization");
            try {
                tokenManager.getIdFromAccessToken(new AccessToken(accessToken));
                return true;
            }catch (NullPointerException e){
                ErrorMessage errorMessage = ErrorMessage.builder()
                        .code(401)
                        .message("엑세스 토큰이 없습니다.")
                        .path(request.getRequestURI())
                        .method(HttpMethod.valueOf(request.getMethod())).build();
                throw new SystemException(errorMessage);
            }
        }
    }
}
