package com.team1.TBBCloneCoding.comment.service;

import com.team1.TBBCloneCoding.comment.dto.CommentCreateRequestDto;
import com.team1.TBBCloneCoding.comment.dto.CommentResponseDto;
import com.team1.TBBCloneCoding.comment.entity.Comment;
import com.team1.TBBCloneCoding.comment.mapper.CommentMapper;
import com.team1.TBBCloneCoding.comment.repository.CommentRepository;
import com.team1.TBBCloneCoding.common.dto.ResponseDto;
import com.team1.TBBCloneCoding.member.entity.Member;
import com.team1.TBBCloneCoding.member.repository.MemberRepository;
import com.team1.TBBCloneCoding.project.entity.Project;
import com.team1.TBBCloneCoding.project.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    private final MemberRepository memberRepository;

    private final ProjectRepository projectRepository;

    private final CommentMapper commentMapper;

    @Transactional
    public ResponseDto createComment(Member member, Long projectId, CommentCreateRequestDto commentCreateRequestDto){

        Long memberId = member.getMemberId();

        Member memberForCreateComment = memberRepository.findById(memberId).orElseThrow(
                () -> new NullPointerException()
        );

        Project project = projectRepository.findById(projectId).orElseThrow(
                () -> new NullPointerException()
        );

        Comment comment = commentMapper.toComment(memberForCreateComment, commentCreateRequestDto, project);

        commentRepository.save(comment);

        return new ResponseDto("success", "댓글을 작성하였습니다", null);

    }

    @Transactional(readOnly = true)
    public ResponseDto getAllComment(Member member, Long projectId) {

        Long memberId = member.getMemberId();

        Boolean commentIsMine = false;

        List<CommentResponseDto> allCommentResponseDto = new ArrayList<>();

        List<Comment> comments = commentRepository.findAllByOrderByLastModifiedAtDesc();

        for(Comment comment : comments) {

            if(comment.getMember().getMemberId().equals(memberId)){
                commentIsMine = true;
            }
            allCommentResponseDto.add(commentMapper.toResponseDto(comment,commentIsMine));
        }

        return new ResponseDto("success","댓글을 조회하였습니다", allCommentResponseDto);

    }
}
