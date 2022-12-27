package com.team1.TBBCloneCoding.project.service;

import com.team1.TBBCloneCoding.common.dto.ResponseDto;
import com.team1.TBBCloneCoding.member.entity.Member;
import com.team1.TBBCloneCoding.member.repository.MemberRepository;
import com.team1.TBBCloneCoding.project.dto.SupportCreateRequestDto;
import com.team1.TBBCloneCoding.project.entity.Project;
import com.team1.TBBCloneCoding.project.entity.ProjectLike;
import com.team1.TBBCloneCoding.project.entity.Support;
import com.team1.TBBCloneCoding.project.mapper.ProjectLikeMapper;
import com.team1.TBBCloneCoding.project.mapper.SupportMapper;
import com.team1.TBBCloneCoding.project.repository.ProjectLikeRepository;
import com.team1.TBBCloneCoding.project.repository.ProjectRepository;
import com.team1.TBBCloneCoding.project.repository.SupportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ProjectService {
    private final MemberRepository memberRepository;
    private final SupportRepository supportRepository;
    private final ProjectRepository projectRepository;
    private final ProjectLikeRepository projectLikeRepository;
    private final SupportMapper supportMapper;
    private final ProjectLikeMapper projectLikeMapper;

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


    @Transactional
    public ResponseDto createProjectLike(Member member, Long projectId){

        Project project = projectRepository.findById(projectId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시물 입니다.")
        );

        Optional<ProjectLike> findProjectLike = projectLikeRepository.findByProjectAndMember(project, member);

        if(findProjectLike.isPresent()){
            projectLikeRepository.deleteByProjectAndMember(project, member);
            return new ResponseDto("success","좋아요 취소 성공", null);
        }

        ProjectLike projectLike = projectLikeMapper.toProjectLike(member, project);
        projectLikeRepository.save(projectLike);

        return new ResponseDto("success","좋아요 등록 성공", null);
    }

}
