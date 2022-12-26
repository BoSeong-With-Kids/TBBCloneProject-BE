package com.team1.TBBCloneCoding.comment.service;

import com.team1.TBBCloneCoding.comment.Dto.CommentCreateRequestDto;
import com.team1.TBBCloneCoding.comment.entity.Comment;
import com.team1.TBBCloneCoding.comment.repository.CommentRepository;
import com.team1.TBBCloneCoding.common.dto.ResponseDto;
import com.team1.TBBCloneCoding.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    @Transactional
    public ResponseDto updateComment(Member member, Long commentId, CommentCreateRequestDto commentCreateRequestDto){

        Long memberId = member.getMemberId();

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new NullPointerException("해당 코멘트를 찾을 수 없습니다")
        );

        if(comment.getMember().getMemberId().equals(memberId)){
            comment.updateComment(commentCreateRequestDto.getContents());

        } else {
            throw new IllegalArgumentException("작성자만 수정할 수 있습니다");
        }

        return new ResponseDto("success", "댓글을 수정하였습니다", null);

    }
}
