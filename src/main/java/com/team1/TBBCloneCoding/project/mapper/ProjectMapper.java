package com.team1.TBBCloneCoding.project.mapper;

import com.team1.TBBCloneCoding.member.entity.Member;
import com.team1.TBBCloneCoding.project.dto.ProjectRequestDto;
import com.team1.TBBCloneCoding.project.entity.Project;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {
    public Project toEntity(ProjectRequestDto projectRequestDto, Member member){
        return Project.builder()
                .category(projectRequestDto.getCategory())
                .title(projectRequestDto.getTitle())
                .summary(projectRequestDto.getSummary())
                .goalPrice(projectRequestDto.getGoalPrice())
                .startDate(projectRequestDto.getEndDate())
                .endDate(projectRequestDto.getEndDate())
                .imageList(projectRequestDto.getImageList())
                .member(member)
                .build();
    }
}
