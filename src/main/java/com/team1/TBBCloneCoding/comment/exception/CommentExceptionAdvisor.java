package com.team1.TBBCloneCoding.comment.exception;

import com.team1.TBBCloneCoding.comment.controller.CommentController;
import com.team1.TBBCloneCoding.common.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = CommentController.class)
public class CommentExceptionAdvisor {

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<ResponseDto> CommentExceptionHandler(IllegalArgumentException e) {
        return new ResponseEntity(new ResponseDto("fail", e.getMessage(), null), HttpStatus.BAD_REQUEST);
    }
}
