package com.team1.TBBCloneCoding.comment.controller;

import com.team1.TBBCloneCoding.comment.dto.CommentCreateRequestDto;
import com.team1.TBBCloneCoding.comment.dto.CommentResponseDto;
import com.team1.TBBCloneCoding.comment.service.CommentService;
import com.team1.TBBCloneCoding.common.dto.ResponseDto;
import com.team1.TBBCloneCoding.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/api/tumblebug")
@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/project/{projectId}/comment")
    public ResponseDto createComment(Member member, @PathVariable Long projectId, @RequestBody CommentCreateRequestDto commentCreateRequestDto) {

        Long memberId = member.getMemberId();

        commentService.createComment(memberId, projectId, commentCreateRequestDto);

        return new ResponseDto("성공","댓글을 작성하였습니다",null);
    }


    @GetMapping("/project/{projectId}/comment")
    public ResponseDto getAllComment(Member member, @PathVariable Long projectId) {

        Long memberId = member.getMemberId();

        List<CommentResponseDto> commentResponseDtoList = commentService.getAllComment(projectId, memberId);

        return new ResponseDto("성공", "댓글 조회에 성공하였습니다", commentResponseDtoList);

    }

}
