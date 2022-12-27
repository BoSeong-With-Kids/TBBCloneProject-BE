package com.team1.TBBCloneCoding.project.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProjectImageResponseDto {
    private Long id;
    private String url;
}
