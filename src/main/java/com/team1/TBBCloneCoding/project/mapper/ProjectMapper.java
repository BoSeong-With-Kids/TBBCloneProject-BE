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

    public ProjectDetailsReadResponseDto entityToProjectDetailsReadResponseDto(Project project, Long totalSupport, int supporterCount, boolean isMine, int projectLike, List<String> thumbnailImageList) {
        return ProjectDetailsReadResponseDto.builder()
                .category(project.getCategory())
                .title(project.getTitle())
                .totalSupport(totalSupport)
                .supporterCount(supporterCount)
                .goalPrice(project.getGoalPrice())
                .startDate(project.getStartDate())
                .endDate(project.getEndDate())
                .projectLike(projectLike)
                .content(project.getContent())
                .projectIsMine(isMine)
                .thumbnailImageListUrl(thumbnailImageList)
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
