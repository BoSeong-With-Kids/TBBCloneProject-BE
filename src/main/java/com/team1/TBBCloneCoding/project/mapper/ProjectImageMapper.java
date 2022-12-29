package com.team1.TBBCloneCoding.project.mapper;

import com.team1.TBBCloneCoding.project.dto.ProjectImageResponseDto;
import com.team1.TBBCloneCoding.project.entity.ProjectImage;
import org.springframework.stereotype.Component;

@Component
public class ProjectImageMapper {
    public ProjectImage entityToProjectImage(String filename, String uploadImageUrl){
        return ProjectImage.builder()
                .imageUrl(uploadImageUrl)
                .filename(filename)
                .build();
    }
    public ProjectImageResponseDto toResponseDto(Long id, String url){
        return ProjectImageResponseDto.builder()
                .id(id)
                .url(url)
                .build();
    }
}
