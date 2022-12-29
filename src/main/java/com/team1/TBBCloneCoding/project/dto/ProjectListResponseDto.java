package com.team1.TBBCloneCoding.project.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Getter
@NoArgsConstructor
public class ProjectListResponseDto {
    private Long projectId;
    private String category;
    private String title;
    private String summary;
    private String nickname;
    private Long totalSupport;
    private Long percent;
    private LocalDateTime endDate;
    private boolean projectLike;

    @Builder
    public ProjectListResponseDto(Long projectId, String category, String title, String summary, String nickname, Long totalSupport, Long percent, LocalDateTime endDate, boolean projectLike) {
        this.projectId = projectId;
        this.category = category;
        this.title = title;
        this.summary = summary;
        this.nickname = nickname;
        this.totalSupport = totalSupport;
        this.percent = percent;
        this.endDate = endDate;
        this.projectLike = projectLike;
    }
}
