package com.team1.TBBCloneCoding.project.exception;

import com.team1.TBBCloneCoding.comment.controller.CommentController;
import com.team1.TBBCloneCoding.common.dto.ResponseDto;
import com.team1.TBBCloneCoding.project.controller.ProjectController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = ProjectController.class)
public class ProjectExceptionAdvisor {

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<ResponseDto> CommentExceptionHandler(IllegalArgumentException e) {
        return new ResponseEntity(new ResponseDto("fail", e.getMessage(), null), HttpStatus.BAD_REQUEST);
    }
}
