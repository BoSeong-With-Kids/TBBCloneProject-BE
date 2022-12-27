package com.team1.TBBCloneCoding.project.mapper;

import com.team1.TBBCloneCoding.project.dto.ProjectDetailsReadResponseDto;
import com.team1.TBBCloneCoding.project.entity.Project;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {

    public ProjectDetailsReadResponseDto entityToProjectDetailsReadResponseDto(Project project, Long totalSupport, int supporterCount, boolean isMine, int projectLike) {
        return ProjectDetailsReadResponseDto.builder()
                .category(project.getCategory())
                .title(project.getTitle())
                .summary(project.getSummary())
                .content(project.getContent())
                .totalSupport(totalSupport)
                .supporterCount(supporterCount)
                .goalPrice(project.getGoalPrice())
                .startDate(project.getStartDate())
                .endDate(project.getEndDate())
                .projectLike(projectLike)
                .projectIsMine(isMine)
                .build();
    }
}
