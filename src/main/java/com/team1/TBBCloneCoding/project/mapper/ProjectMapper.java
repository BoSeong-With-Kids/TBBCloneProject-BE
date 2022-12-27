package com.team1.TBBCloneCoding.project.mapper;

import com.team1.TBBCloneCoding.member.entity.Member;
import com.team1.TBBCloneCoding.project.dto.ProjectCreateRequestDto;
import com.team1.TBBCloneCoding.project.entity.Project;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {
    public Project toEntity(ProjectCreateRequestDto projectCreateRequestDto, Member member){
        return Project.builder()
                .category(projectCreateRequestDto.getCategory())
                .title(projectCreateRequestDto.getTitle())
                .summary(projectCreateRequestDto.getSummary())
                .content(projectCreateRequestDto.getContent())
                .goalPrice(projectCreateRequestDto.getGoalPrice())
                .startDate(projectCreateRequestDto.getEndDate())
                .endDate(projectCreateRequestDto.getEndDate())
                .member(member)
                .build();
    }
}
