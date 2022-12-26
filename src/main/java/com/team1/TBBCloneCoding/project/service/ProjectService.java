package com.team1.TBBCloneCoding.project.service;

import com.team1.TBBCloneCoding.common.dto.ResponseDto;
import com.team1.TBBCloneCoding.member.entity.Member;
import com.team1.TBBCloneCoding.project.dto.ProjectCreateRequestDto;
import com.team1.TBBCloneCoding.project.dto.ProjectUpdateRequestDto;
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
    public ResponseDto updateProject(Long projectId, ProjectUpdateRequestDto projectUpdateRequestDto, Member member) {
        Project project = projectRepository.findById(projectId).orElseThrow(
                () -> new IllegalArgumentException("projectId로 데이터베이스에서 프로젝트를 찾을 수 없습니다.")
        );

        if(!member.getLoginId().equals(project.getMember().getLoginId())){
            throw new IllegalArgumentException("프로젝트를 생성한 사용자가 아닙니다.");
        }


        //project.update(projectUpdateRequestDto);
        project = projectMapper.projectUpdateRequestDtoToEntity(projectUpdateRequestDto);

        return new ResponseDto("success","프로젝트 수정에 성공했습니다.",null);
    }
}
