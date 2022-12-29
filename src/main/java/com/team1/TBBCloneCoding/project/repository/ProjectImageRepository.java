package com.team1.TBBCloneCoding.project.repository;

import com.team1.TBBCloneCoding.project.entity.Project;
import com.team1.TBBCloneCoding.project.entity.ProjectImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectImageRepository extends JpaRepository<ProjectImage, Long> {
    ProjectImage findByImageUrl(String uploadImageUrl);
    void deleteById(Long id);
    List<ProjectImage> findAllByProjectAndWhichContent(Project project, String thumbnailImage);
    ProjectImage findByProjectAndWhichContent(Project project, String thumbnailImage);
}
