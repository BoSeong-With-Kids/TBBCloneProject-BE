package com.team1.TBBCloneCoding.project.dto;

import lombok.Builder;

import java.time.LocalDateTime;


@Builder
public class ProjectListResponseDto {
    private Long projectId;
    private String category;
    private String title;
    private String summary;
    private String nickname;
    private Long totalSupport;
    private Long percent;
    private LocalDateTime endDate;
    private int projectLike;
}
