package com.team1.TBBCloneCoding.project.dto;

import com.team1.TBBCloneCoding.project.entity.ProjectImage;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

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
    private int projectLike;
    private String content;
    private boolean projectIsMine;
    private List<String> thumbnailImageListUrl;
}
