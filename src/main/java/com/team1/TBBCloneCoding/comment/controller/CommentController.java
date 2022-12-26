package com.team1.TBBCloneCoding.comment.controller;

import com.team1.TBBCloneCoding.comment.dto.CommentCreateRequestDto;
import com.team1.TBBCloneCoding.comment.service.CommentService;
import com.team1.TBBCloneCoding.common.dto.ResponseDto;
import com.team1.TBBCloneCoding.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tumblebug")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/project/{projectId}/comment")
    public ResponseEntity<ResponseDto> createComment(Member member, @PathVariable Long projectId, @RequestBody CommentCreateRequestDto commentCreateRequestDto) {

        ResponseDto responseDto = commentService.createComment(member, projectId, commentCreateRequestDto);

        return new ResponseEntity(responseDto, HttpStatus.OK) ;
    }

    @GetMapping("/project/{projectId}/comment")
    public ResponseEntity<ResponseDto> getAllComment(Member member, @PathVariable Long projectId) {

        ResponseDto responseDto = commentService.getAllComment(member, projectId);

        return new ResponseEntity(responseDto, HttpStatus.OK);

    }

    @DeleteMapping("/project/{projectId}/comment/{commentId}")
    public ResponseEntity<ResponseDto> deleteComment(Member member, @PathVariable Long commentId){

        ResponseDto responseDto = commentService.deleteComment(member, commentId);

        return new ResponseEntity(responseDto, HttpStatus.OK);

    }
}
