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

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;
    @Transactional
    public ResponseDto createProject(ProjectCreateRequestDto projectCreateRequestDto, Member member) {
        Project project = projectMapper.toEntity(projectCreateRequestDto, member);
        projectRepository.save(project);
        return new ResponseDto("success","프로젝트 등록에 성공했습니다.",null);
    }

    @Transactional
    public ResponseDto updateProject(Long projectId, ProjectUpdateRequestDto projectUpdateRequestDto, Member member) {
        Project project = projectRepository.findById(projectId).orElseThrow(
                () -> new IllegalArgumentException("projectId로 데이터베이스에서 프로젝트를 찾을 수 없습니다.")
        );

        if(!member.getLoginId().equals(project.getMember().getLoginId())){
            throw new IllegalArgumentException("프로젝트를 생성한 사용자가 아닙니다.");
        }

        project.update(projectUpdateRequestDto);
        return new ResponseDto("success","프로젝트 수정에 성공했습니다.",null);

    }

    @Transactional(readOnly = true)
    public ResponseDto getProjectList(String filter, String category) {

        // 기본값 : 최신순 filter(latest)
        List<Project> projectList = projectRepository.findAllByCategoryOrderByCreatedAtDesc(category);
        // filter에 따라서 정렬순서변경
        if(filter.equals("oldest")){
            // 오래된순
            projectList = projectRepository.findAllByCategoryOrderByCreatedAtAsc(category);
        }
        else if(filter.equals("popular")){
            // 인기순
           projectList = projectRepository.findAllByCategoryOrderByRecommendCountDesc(category);
        }
        return new ResponseDto("success", "프로젝트 리스트 조회에 성공했습니다.", projectList);
    }
}
