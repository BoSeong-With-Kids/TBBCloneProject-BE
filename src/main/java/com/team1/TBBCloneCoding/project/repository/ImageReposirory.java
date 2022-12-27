package com.team1.TBBCloneCoding.project.repository;

import com.team1.TBBCloneCoding.project.entity.Image;
import com.team1.TBBCloneCoding.project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageReposirory extends JpaRepository<Image, Long> {
    List<Image> findAllByProjectAndWhichContent(Project project, String whichContent);
}
