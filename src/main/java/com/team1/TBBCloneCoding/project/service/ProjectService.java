package com.team1.TBBCloneCoding.project.service;

import com.team1.TBBCloneCoding.common.dto.ResponseDto;
import com.team1.TBBCloneCoding.project.entity.Project;
import com.team1.TBBCloneCoding.project.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
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
