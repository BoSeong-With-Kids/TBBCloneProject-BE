package com.team1.TBBCloneCoding.member.exception;

import com.team1.TBBCloneCoding.common.dto.ResponseDto;
import com.team1.TBBCloneCoding.member.controller.MemberController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = MemberController.class)
public class MemberExceptionAdvisor {

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<ResponseDto> MemberExceptionHandler(IllegalArgumentException e) {
        return new ResponseEntity(new ResponseDto("fail", e.getMessage(), null), HttpStatus.BAD_REQUEST);
    }
}
