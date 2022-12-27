package com.team1.TBBCloneCoding.project.exception;

import com.team1.TBBCloneCoding.common.dto.ResponseDto;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseDto handleValidRequest(MethodArgumentNotValidException e){
        if(e.getMessage().equals("30자 미만으로 작성해주세요")){
            return new ResponseDto<>("fail","제목을 30자 미만으로 작성해서 보내주세요",null);
        }
        if(e.getMessage().equals("150자 미만으로 작성해주세요")){
            return new ResponseDto<>("fail", "요약본을 150자 미만으로 작성해서 보내주세요", null);
        }
        return new ResponseDto<>("fail","Valid 에러가 발생했습니다", null);
    }
}
