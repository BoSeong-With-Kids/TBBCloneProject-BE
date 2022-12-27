package com.team1.TBBCloneCoding.project.mapper;

import com.team1.TBBCloneCoding.project.dto.ProjectDetailsReadResponseDto;
import com.team1.TBBCloneCoding.member.entity.Member;
import com.team1.TBBCloneCoding.project.dto.ProjectCreateRequestDto;
import com.team1.TBBCloneCoding.project.dto.ProjectUpdateRequestDto;
import com.team1.TBBCloneCoding.project.dto.ProjectListResponseDto;
import com.team1.TBBCloneCoding.project.entity.Project;
import com.team1.TBBCloneCoding.project.entity.ProjectImage;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProjectMapper {

    public ProjectDetailsReadResponseDto entityToProjectDetailsReadResponseDto(Project project, Long totalSupport, int supporterCount, boolean isMine, int projectLike, List<ProjectImage> thumbnailImageList) {
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
                .thumbnailImageList(thumbnailImageList)
                .build();
    }
    
    public Project projectUpdateRequestDtoToEntity(ProjectUpdateRequestDto projectUpdateRequestDto) {
        return Project.builder()
                .category(projectUpdateRequestDto.getCategory())
                .title(projectUpdateRequestDto.getTitle())
                .summary(projectUpdateRequestDto.getSummary())
                .goalPrice(projectUpdateRequestDto.getGoalPrice())
                .startDate(projectUpdateRequestDto.getStartDate())
                .endDate(projectUpdateRequestDto.getEndDate())
                .build();
    }

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
