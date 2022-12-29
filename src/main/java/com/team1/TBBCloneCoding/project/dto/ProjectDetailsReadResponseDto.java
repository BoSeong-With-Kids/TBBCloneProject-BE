package com.team1.TBBCloneCoding.project.dto;

import com.team1.TBBCloneCoding.project.entity.ProjectImage;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class ProjectDetailsReadResponseDto {
    private String category;
    private String title;
    private String summary;
    private Long totalSupport;
    private int supporterCount;
    private Long goalPrice;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private boolean projectLike;
    private int projectLikeCount;
    private String content;
    private boolean projectIsMine;
    private List<String> thumbnailImageListUrl;

    @Builder
    public ProjectDetailsReadResponseDto(String category, String title, String summary, Long totalSupport, int supporterCount, Long goalPrice, LocalDateTime startDate, LocalDateTime endDate, boolean projectLike ,int projectLikeCount, String content, boolean projectIsMine, List<String> thumbnailImageListUrl) {
        this.category = category;
        this.title = title;
        this.summary = summary;
        this.totalSupport = totalSupport;
        this.supporterCount = supporterCount;
        this.goalPrice = goalPrice;
        this.startDate = startDate;
        this.endDate = endDate;
        this.projectLike = projectLike;
        this.projectLikeCount = projectLikeCount;
        this.content = content;
        this.projectIsMine = projectIsMine;
        this.thumbnailImageListUrl = thumbnailImageListUrl;
    }
}
