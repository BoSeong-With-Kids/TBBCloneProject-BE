package com.team1.TBBCloneCoding.project.repository;

import com.team1.TBBCloneCoding.member.entity.Member;
import com.team1.TBBCloneCoding.project.entity.Project;
import com.team1.TBBCloneCoding.project.entity.ProjectLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectLikeRepository extends JpaRepository<ProjectLike, Long> {
    Optional<ProjectLike> findByProjectAndMember(Project project, Member member);
    int countByProject(Project project);
    void deleteByProjectAndMember(Project project, Member member);

    Optional<ProjectLike> findByProject(Project project);
}
