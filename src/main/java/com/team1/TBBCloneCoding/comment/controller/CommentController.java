package com.team1.TBBCloneCoding.comment.controller;

import com.team1.TBBCloneCoding.comment.Dto.CommentCreateRequestDto;
import com.team1.TBBCloneCoding.comment.service.CommentService;
import com.team1.TBBCloneCoding.common.dto.ResponseDto;
import com.team1.TBBCloneCoding.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/tumblebug")
@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PutMapping("/project/{projectId}/comment/{commentId}")
    public ResponseEntity<ResponseDto> updateComment(Member member, @PathVariable Long commentId, @RequestBody CommentCreateRequestDto commentCreateRequestDto) {

        ResponseDto responseDto = commentService.updateComment(member, commentId, commentCreateRequestDto);

        return new ResponseEntity(responseDto, HttpStatus.OK);

    }
}
