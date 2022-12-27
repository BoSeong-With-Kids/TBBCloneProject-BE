package com.team1.TBBCloneCoding.project.mapper;

import com.team1.TBBCloneCoding.project.dto.ProjectListResponseDto;
import com.team1.TBBCloneCoding.project.entity.Project;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {
    public ProjectListResponseDto entityToProjectListResponseDto(Project project) {
        return ProjectListResponseDto.builder()
                .
                .build();
    }
}
