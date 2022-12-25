package com.team1.TBBCloneCoding.project.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class ProjectDetailsReadResponseDto {
    private String category;
    private String title;
    private String summary;
    private Long totalSupport;
    private int supporterCount;
    private Long goalPrice;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private boolean projectIsMine;
}
