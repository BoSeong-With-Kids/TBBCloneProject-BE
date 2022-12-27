package com.team1.TBBCloneCoding.project.service;

import com.team1.TBBCloneCoding.common.dto.ResponseDto;
import com.team1.TBBCloneCoding.member.entity.Member;
import com.team1.TBBCloneCoding.project.dto.ProjectDetailsReadResponseDto;
import com.team1.TBBCloneCoding.project.entity.Like;
import com.team1.TBBCloneCoding.project.entity.Project;
import com.team1.TBBCloneCoding.project.entity.Support;
import com.team1.TBBCloneCoding.project.mapper.ProjectMapper;
import com.team1.TBBCloneCoding.project.repository.LikeRepository;
import com.team1.TBBCloneCoding.project.repository.ProjectRepository;
import com.team1.TBBCloneCoding.project.repository.SupportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final SupportRepository supportRepository;
    private final ProjectMapper projectMapper;
    private final LikeRepository likeRepository;


    @Transactional(readOnly = true)
    public ResponseDto getProjectDetails(Long projectId, Member member) {

        Project project = projectRepository.findById(projectId).orElseThrow(
                () -> new NullPointerException("projectId로 불러 올 수 있는 프로젝트가 없습니다.")
        );

        // 특정 projectId가 담겨있는 support들의 List 불러와서 totalSupport, supporterCount 구하는 로직
        List<Support> supportList = supportRepository.findAllByProject(project);
        int supporterCount = supportList.size();
        Long totalSupport = 0L;
        for(Support support : supportList){
            // totalSupport
            totalSupport = totalSupport + support.getSupportAmount();
        }

        // ProjectDetails를 불러오는 사람이 이 프로젝트를 올린 사람인지 확인하는 로직 :: 같은 사람이 맞다면 => "isMine = true", 같은 사람이 아니면 => "isMine = false"
        boolean isMine = false;
        if(member.getMemberId() == project.getMember().getMemberId()){
            isMine = true;
        }

        int projectLike = likeRepository.findAllByProject(project).size();

        ProjectDetailsReadResponseDto projectDetailsReadResponseDto = projectMapper.entityToProjectDetailsReadResponseDto(project, totalSupport, supporterCount, isMine, projectLike);
        return new ResponseDto("success","리스트 조회 성공", projectDetailsReadResponseDto);
    }


}
