package com.team1.TBBCloneCoding.project.dto;

import lombok.Builder;

import java.time.LocalDateTime;


@Builder
public class ProjectListResponseDto {
    private Long projectId;
    private String category;
    private String title;
    private String nickname;
    private Long totalSupport;
    private Double percent;
    private LocalDateTime endDate;
    private String projectLike;
}
