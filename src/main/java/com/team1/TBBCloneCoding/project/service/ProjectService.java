package com.team1.TBBCloneCoding.project.service;

import com.team1.TBBCloneCoding.common.dto.ResponseDto;
import com.team1.TBBCloneCoding.member.entity.Member;
import com.team1.TBBCloneCoding.project.dto.ProjectCreateRequestDto;
import com.team1.TBBCloneCoding.project.entity.Project;
import com.team1.TBBCloneCoding.project.mapper.ProjectMapper;
import com.team1.TBBCloneCoding.project.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;
    @Transactional
    public ResponseDto createProject(ProjectCreateRequestDto projectCreateRequestDto, Member member) {
        Project project = projectMapper.toEntity(projectCreateRequestDto, member);
        projectRepository.save(project);
        return new ResponseDto("success","프로젝트 등록에 성공하셨습니다.",null);
    }
}
