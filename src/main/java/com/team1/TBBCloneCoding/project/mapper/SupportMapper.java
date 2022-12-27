package com.team1.TBBCloneCoding.project.mapper;

import com.team1.TBBCloneCoding.member.entity.Member;
import com.team1.TBBCloneCoding.project.dto.SupportCreateRequestDto;
import com.team1.TBBCloneCoding.project.entity.Project;
import com.team1.TBBCloneCoding.project.entity.Support;
import org.springframework.stereotype.Component;

@Component
public class SupportMapper {
    public Support toSupport(Member member, Project project, SupportCreateRequestDto supportCreateRequestDto) {

        return Support.builder()
                .supportAmount(supportCreateRequestDto.getSupportAmount())
                .member(member)
                .project(project)
                .build();

    }

}
