package com.team1.TBBCloneCoding.project.repository;

import com.team1.TBBCloneCoding.project.entity.ProjectLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<ProjectLike, Long> {
}
