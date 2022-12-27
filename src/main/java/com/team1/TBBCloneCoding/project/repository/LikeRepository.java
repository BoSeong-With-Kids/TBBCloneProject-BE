package com.team1.TBBCloneCoding.project.repository;

import com.team1.TBBCloneCoding.project.entity.Like;
import com.team1.TBBCloneCoding.project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {
    List<Like> findAllByProject(Project project);
}
