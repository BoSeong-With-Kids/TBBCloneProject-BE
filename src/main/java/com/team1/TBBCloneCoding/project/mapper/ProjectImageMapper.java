package com.team1.TBBCloneCoding.project.mapper;

import com.team1.TBBCloneCoding.project.dto.ProjectImageResponseDto;
import com.team1.TBBCloneCoding.project.entity.ProjectImage;
import org.springframework.stereotype.Component;

@Component
public class ProjectImageMapper {
    //public Support toSupport(Member member, Project project, SupportCreateRequestDto supportCreateRequestDto) {
    //
    //    return Support.builder()
    //            .supportAmount(supportCreateRequestDto.getSupportAmount())
    //            .member(member)
    //            .project(project)
    //            .build();
    //
    //}
    public ProjectImage entityToProjectImage(String uploadImageUrl){
        return ProjectImage.builder()
                .imageUrl(uploadImageUrl)
                .build();
    }

    public ProjectImageResponseDto toResponseDto(Long id, String url){
        return ProjectImageResponseDto.builder()
                .id(id)
                .url(url)
                .build();
    }

}
