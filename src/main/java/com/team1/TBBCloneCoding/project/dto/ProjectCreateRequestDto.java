package com.team1.TBBCloneCoding.project.dto;

import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ProjectCreateRequestDto {
    private String category;
    @Size(min=0,max=100, message = "100자 미만으로 작성해주세요")
    private String title;
    @Size(min=0,max=150, message = "150자 미만으로 작성해주세요")
    private String summary;
    private Long goalPrice;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime endDate;
    private String content;
    private String thumbnailImageUrl;
    private List<Long> contentImageListPk;
}
