package com.team1.TBBCloneCoding.project.repository;

import com.team1.TBBCloneCoding.project.entity.ProjectImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageReposirory extends JpaRepository<ProjectImage, Long> {
}
