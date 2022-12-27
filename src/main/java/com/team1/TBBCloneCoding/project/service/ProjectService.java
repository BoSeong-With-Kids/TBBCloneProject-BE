package com.team1.TBBCloneCoding.project.service;

import com.team1.TBBCloneCoding.common.dto.ResponseDto;
import com.team1.TBBCloneCoding.member.entity.Member;
import com.team1.TBBCloneCoding.member.repository.MemberRepository;
import com.team1.TBBCloneCoding.project.dto.SupportCreateRequestDto;
import com.team1.TBBCloneCoding.project.entity.Project;
import com.team1.TBBCloneCoding.project.entity.Support;
import com.team1.TBBCloneCoding.project.mapper.SupportMapper;
import com.team1.TBBCloneCoding.project.repository.ProjectRepository;
import com.team1.TBBCloneCoding.project.repository.SupportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class ProjectService {

    private final MemberRepository memberRepository;
    private final SupportRepository supportRepository;
    private final ProjectRepository projectRepository;
    private final SupportMapper supportMapper;

    @Transactional
    public ResponseDto createSupport(Member member, Long projectId, SupportCreateRequestDto supportCreateRequestDto){

        Long memberId = member.getMemberId();

        Member memberForCreateSupport = memberRepository.findById(memberId).orElseThrow(
            () -> new NullPointerException()
        );

        Project project = projectRepository.findById(projectId).orElseThrow(
            () -> new NullPointerException()
        );

        Support support = supportMapper.toSupport(memberForCreateSupport, project, supportCreateRequestDto);

        supportRepository.save(support);

        return new ResponseDto("success","후원 성공", null);

}
}
