package com.team1.TBBCloneCoding.comment.service;

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
    public ResponseDto deleteComment(Member member, Long commentId){

        Long memberId = member.getmemberId();

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new NullPointerException()
        );

        if(comment.getMember().getMemberId().equals(memberId)){
            commentRepository.delete(comment);
        } else {
            throw new IllegalArgumentException("작성자만 삭제할 수 있습니다");
        }

            return new ResponseDto("success", "댓글을 삭제하였습니다", null);
    }
}
