package com.team1.TBBCloneCoding.project.mapper;

import com.team1.TBBCloneCoding.member.entity.Member;
import com.team1.TBBCloneCoding.project.dto.SupportCreateRequestDto;
import com.team1.TBBCloneCoding.project.entity.Project;
import com.team1.TBBCloneCoding.project.entity.Support;

public class SupportMapper {
    public Support toSupport(Member member, Project project, SupportCreateRequestDto supportCreateRequestDto) {
        return new Support(supportCreateRequestDto.getSupportAmount(), member, project);
    }

}
