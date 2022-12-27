package com.team1.TBBCloneCoding.project.mapper;

import com.team1.TBBCloneCoding.project.dto.ProjectListResponseDto;
import com.team1.TBBCloneCoding.project.entity.Project;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {
    public ProjectListResponseDto entityToProjectListResponseDto(Project project, Long totalSupport, Long percent, int projectLike) {
        return ProjectListResponseDto.builder()
                .projectId(project.getProjectId())
                .category(project.getCategory())
                .title(project.getTitle())
                .summary(project.getSummary())
                .nickname(project.getMember().getNickname())
                .totalSupport(totalSupport)
                .percent(percent)
                .endDate(project.getEndDate())
                .projectLike(projectLike)
                .build();
    }
}
