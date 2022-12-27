package com.team1.TBBCloneCoding.project.dto;

import lombok.Getter;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ProjectCreateRequestDto {
    private String category;
    @Size(min=0,max=30, message = "30자 미만으로 작성해주세요")
    private String title;
    @Size(min=0,max=150, message = "150자 미만으로 작성해주세요")
    private String summary;
    private String content;
    private Long goalPrice;
    private LocalDateTime statDate;
    private LocalDateTime endDate;
    private List<Long> thumbnailList;
    private List<Long> contentImageList;
}
