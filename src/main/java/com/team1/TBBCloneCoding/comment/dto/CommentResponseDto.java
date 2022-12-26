package com.team1.TBBCloneCoding.comment.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class CommentResponseDto {

    private Long commentId;
    private String contents;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Boolean commentIsMine;

}
