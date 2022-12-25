package com.team1.TBBCloneCoding.comment.mapper;

import com.team1.TBBCloneCoding.comment.dto.CommentCreateRequestDto;
import com.team1.TBBCloneCoding.comment.dto.CommentResponseDto;
import com.team1.TBBCloneCoding.comment.entity.Comment;
import com.team1.TBBCloneCoding.member.entity.Member;
import com.team1.TBBCloneCoding.project.entity.Project;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public Comment toComment(Member member, CommentCreateRequestDto commentCreateRequestDto, Project project){
        return new Comment(commentCreateRequestDto.getContents(), project, member);
    }

    public CommentResponseDto toResponseDto(Comment comment, Boolean commentIsMine){
        return CommentResponseDto.builder()
                .commentId(comment.getCommentId())
                .contents(comment.getContents())
                .createdAt(comment.getCreatedAt())
                .modifiedAt(comment.getModifiedAt())
                .commentIsMine(commentIsMine)
                .build();

    }

}
