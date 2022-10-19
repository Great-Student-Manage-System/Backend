package com.example.demo.exception;

import com.example.demo.model.dto.response.ErrorMessage;
import lombok.Getter;
import org.springframework.lang.NonNull;

/**
 * 이 시스템의 비즈니스 로직에서 발생 가능한 모든 예외적인 상황에서 투척되는 예외 입니다.
 * 이 시스템에서 어떤 예외가 발생하였을때 최종적으로 이 예외가 던져지는 것을 강력히 권고 합니다.
 */
public class SystemException extends RuntimeException{
    @NonNull
    @Getter
    private final ErrorMessage errorMessage;

    /**
     * 예외가 발생한 원인과 위치 등을 포함하는 메시지를 포함한 예외를 생성합니다.
     * @param errorMessage 클라이언트에 전달할 에러메시지
     */
    public SystemException(ErrorMessage errorMessage){
        super(errorMessage.getMessage());
        this.errorMessage = errorMessage;
    }


}
