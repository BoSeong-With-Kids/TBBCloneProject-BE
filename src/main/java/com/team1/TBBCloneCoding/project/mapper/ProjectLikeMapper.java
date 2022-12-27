package com.team1.TBBCloneCoding.project.mapper;

import com.team1.TBBCloneCoding.member.entity.Member;
import com.team1.TBBCloneCoding.project.dto.SupportCreateRequestDto;
import com.team1.TBBCloneCoding.project.entity.Project;
import com.team1.TBBCloneCoding.project.entity.ProjectLike;
import com.team1.TBBCloneCoding.project.entity.Support;
import org.springframework.stereotype.Component;

@Component
public class ProjectLikeMapper {

    public ProjectLike toProjectLike(Member member, Project project) {

        return ProjectLike.builder()
                .member(member)
                .project(project)
                .build();

    }
}
