package com.team1.TBBCloneCoding.project.repository;

import com.team1.TBBCloneCoding.project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findAllByCategoryOrderByCreatedAtDesc(String category);
    List<Project> findAllByCategoryOrderByCreatedAtAsc(String category);
    List<Project> findAllByOrderByCreatedAtAsc();
    List<Project> findAllByOrderByCreatedAtDesc();
}
