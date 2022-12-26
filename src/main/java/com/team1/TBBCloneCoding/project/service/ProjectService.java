package com.team1.TBBCloneCoding.project.service;

import com.team1.TBBCloneCoding.common.dto.ResponseDto;
import com.team1.TBBCloneCoding.member.entity.Member;
import com.team1.TBBCloneCoding.project.dto.ProjectCreateRequestDto;
import com.team1.TBBCloneCoding.project.entity.Image;
import com.team1.TBBCloneCoding.project.entity.Project;
import com.team1.TBBCloneCoding.project.mapper.ProjectMapper;
import com.team1.TBBCloneCoding.project.repository.ImageReposirory;
import com.team1.TBBCloneCoding.project.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ImageReposirory imageReposirory;
    private final ProjectMapper projectMapper;

    @Transactional
    public ResponseDto createProject(ProjectCreateRequestDto projectCreateRequestDto, Member member) {
        Project project = projectMapper.toEntity(projectCreateRequestDto, member);
        projectRepository.save(project);

        List<Long> imageNumberList = projectCreateRequestDto.getImageList();
        Image image;
        for(Long i : imageNumberList){
            // 저장된 이미지를 레포지토리 가져와서 연결
            image = imageReposirory.findById(i).orElseThrow(
                    () -> new NullPointerException("id에 맞는 이미지가 데이터베이스에 존재하지 않습니다.")
            );
            image.imageConnectionWithProject(project);
        }

        return new ResponseDto("success","프로젝트 등록에 성공하셨습니다.",null);
    }
}
