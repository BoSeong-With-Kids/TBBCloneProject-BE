package com.team1.TBBCloneCoding.project.service;

import com.team1.TBBCloneCoding.common.dto.ResponseDto;
import com.team1.TBBCloneCoding.project.dto.ProjectListResponseDto;
import com.team1.TBBCloneCoding.project.entity.Project;
import com.team1.TBBCloneCoding.project.entity.Support;
import com.team1.TBBCloneCoding.project.mapper.ProjectMapper;
import com.team1.TBBCloneCoding.project.repository.LikeRepository;
import com.team1.TBBCloneCoding.project.repository.ProjectRepository;
import com.team1.TBBCloneCoding.project.repository.SupportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;
    private final SupportRepository supportRepository;
    private final LikeRepository likeRepository;
    @Transactional(readOnly = true)
    public ResponseDto getProjectList(String filter, String category) {


        List<Project> projectList;
        // filter에 따라서 정렬순서변경
        if(filter.equals("oldest")){
            // 오래된순
            projectList = projectRepository.findAllByCategoryOrderByCreatedAtAsc(category);
        }
        else if(filter.equals("popular")){
            // 인기순
           projectList = projectRepository.findAllByCategoryOrderByRecommendCountDesc(category);
        }
        // 기본값 : 최신순 filter(latest)
        projectList = projectRepository.findAllByCategoryOrderByCreatedAtDesc(category);

        // projectList에서 project를 뽑아서 projectListResponseDto로 변환해서 전달
        ProjectListResponseDto projectListResponseDto;
        List<Support> supportList;
        List<ProjectListResponseDto> projectListResponseDtoList = new ArrayList<>();
        for(Project project : projectList){

            // totalSupport, percent 변수선언, goalPrice 불러오기
            Long totalSupport = 0L;
            Long goalPrice = project.getGoalPrice();
            Double percent = 0.0;

            // totalSupport 구하기
            supportList = supportRepository.findAllByProject(project);
            for(Support support : supportList){
                totalSupport = totalSupport + support.getSupportAmount();
            }

            // percent = totalSupport/goalPrice
            percent = Double.valueOf(totalSupport / goalPrice);

            // percent 소숫점 자르기
            percent = Math.floor(percent);
            Long longPercent = percent.longValue();

            // 좋아요 갯수 반환
            int projectLike = likeRepository.findAllByProject(project).size();

            projectListResponseDto = projectMapper.entityToProjectListResponseDto(project,totalSupport,longPercent,projectLike);
            projectListResponseDtoList.add(projectListResponseDto);
        }

        return new ResponseDto("success", "프로젝트 리스트 조회에 성공했습니다.", projectListResponseDtoList);
    }
}
