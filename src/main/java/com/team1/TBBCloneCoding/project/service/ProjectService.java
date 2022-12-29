package com.team1.TBBCloneCoding.project.service;

import com.team1.TBBCloneCoding.comment.entity.Comment;
import com.team1.TBBCloneCoding.comment.repository.CommentRepository;
import com.team1.TBBCloneCoding.common.dto.ResponseDto;
import com.team1.TBBCloneCoding.member.entity.Member;
import com.team1.TBBCloneCoding.member.repository.MemberRepository;
import com.team1.TBBCloneCoding.project.dto.*;
import com.team1.TBBCloneCoding.project.entity.*;
import com.team1.TBBCloneCoding.project.entity.Project;
import com.team1.TBBCloneCoding.project.entity.Support;
import com.team1.TBBCloneCoding.project.mapper.ProjectLikeMapper;
import com.team1.TBBCloneCoding.project.mapper.ProjectMapper;
import com.team1.TBBCloneCoding.project.mapper.SupportMapper;
import com.team1.TBBCloneCoding.project.repository.ProjectImageRepository;
import com.team1.TBBCloneCoding.project.repository.ProjectLikeRepository;
import com.team1.TBBCloneCoding.project.repository.ProjectRepository;
import com.team1.TBBCloneCoding.project.repository.SupportRepository;
import com.team1.TBBCloneCoding.security.jwt.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final MemberRepository memberRepository;
    private final ProjectRepository projectRepository;
    private final SupportRepository supportRepository;
    private final CommentRepository commentRepository;
    private final ProjectMapper projectMapper;
    private final SupportMapper supportMapper;
    private final ProjectLikeMapper projectLikeMapper;
    private final ProjectLikeRepository projectLikeRepository;
    private final ProjectImageRepository projectImageRepository;

    private final JwtUtil jwtUtil;

    @Transactional
    public ResponseDto createProject(ProjectCreateRequestDto projectCreateRequestDto, Member member) {
        Project project = projectMapper.toEntity(projectCreateRequestDto, member);
        projectRepository.save(project);

        String thumbUrl = projectCreateRequestDto.getThumbnailImageUrl();
        ProjectImage thumbImage = projectImageRepository.findByImageUrl(thumbUrl);
        thumbImage.thumbnailImageConnectionWithProject(project);

        ProjectImage image;
        List<Long> imageNumberList = projectCreateRequestDto.getContentImageListPk();
        for(Long i : imageNumberList){
            // 저장된 이미지를 레포지토리 가져와서 연결
            image = projectImageRepository.findById(i).orElseThrow(
                    () -> new NullPointerException("id에 맞는 이미지가 콘텐트이미지 데이터베이스에 존재하지 않습니다.")
            );
            image.contentImageConnectionWithProject(project);
        }

        return new ResponseDto("success","프로젝트 등록에 성공하셨습니다.",null);
    }

    @Transactional
    public ResponseDto updateProject(Long projectId, ProjectUpdateRequestDto request) {
        Project project = projectRepository.findById(projectId).orElseThrow(
                () -> new IllegalArgumentException("projectId로 데이터베이스에서 프로젝트를 찾을 수 없습니다.")
        );

        project.update(request.getCategory(), request.getTitle(), request.getSummary(), request.getContent(), request.getGoalPrice(), request.getStartDate(), request.getEndDate());
        return new ResponseDto("success", "프로젝트 수정에 성공했습니다.", null);
    }

    @Transactional(readOnly = true)
    public ResponseDto getProjectList(String filter, String category) {

        List<Project> projectList;
        // filter에 따라서 정렬순서변경
        if (filter.equals("oldest")) {
            // 오래된순
            if (category.equals("all")) {
                projectList = projectRepository.findAllByOrderByCreatedAtAsc();
            } else {
                projectList = projectRepository.findAllByCategoryOrderByCreatedAtAsc(category);
            }
        } else if (filter.equals("popular")) {
            // 인기순
            if (category.equals("all")) {
                projectList = projectRepository.findAllByOrderByCreatedAtAsc();
            } else {
                projectList = projectRepository.findAllByCategoryOrderByCreatedAtAsc(category);
            }
        }
        // 기본값 : 최신순 filter(latest)
        if (category.equals("all")) {
            projectList = projectRepository.findAllByOrderByCreatedAtDesc();
        } else {
            projectList = projectRepository.findAllByCategoryOrderByCreatedAtDesc(category);
        }

        // projectList에서 project를 뽑아서 projectListResponseDto로 변환해서 전달
        ProjectListResponseDto projectListResponseDto;
        List<Support> supportList;
        List<ProjectListResponseDto> projectListResponseDtoList = new ArrayList<>();
        for (Project project : projectList) {

            // totalSupport, percent 변수선언, goalPrice 불러오기
            Long totalSupport = 0L;
            Long goalPrice = project.getGoalPrice();
            Double percent = 0.0;

            // totalSupport 구하기
            supportList = supportRepository.findAllByProject(project);
            for (Support support : supportList) {
                totalSupport = totalSupport + support.getSupportAmount();
            }

            // percent = totalSupport/goalPrice
            percent = Double.valueOf((totalSupport / goalPrice)*100);

            // percent 소숫점 자르기
            percent = Math.floor(percent);
            Long longPercent = percent.longValue();

            // 좋아요 갯수 반환
            // int projectLike = projectLikeRepository.countByProject(project);
            Optional<ProjectLike> tf = projectLikeRepository.findByProject(project);
            boolean projectLike = false;
            if(tf != null) {
                projectLike = true;
            }
            projectListResponseDto = projectMapper.entityToProjectListResponseDto(project, totalSupport, longPercent, projectLike);
            projectListResponseDtoList.add(projectListResponseDto);
        }

        return new ResponseDto("success", "프로젝트 리스트 조회에 성공했습니다.", projectListResponseDtoList);
    }

    @Transactional(readOnly = true)
    public ResponseDto getProjectDetails(Long projectId, HttpServletRequest request) {

        Project project = projectRepository.findById(projectId).orElseThrow(
                () -> new NullPointerException("projectId로 불러 올 수 있는 프로젝트가 없습니다.")
        );

        // 특정 projectId가 담겨있는 support들의 List 불러와서 totalSupport, supporterCount 구하는 로직
        List<Support> supportList = supportRepository.findAllByProject(project);
        int supporterCount = supportList.size();
        Long totalSupport = 0L;
        for (Support support : supportList) {
            // totalSupport
            totalSupport = totalSupport + support.getSupportAmount();
        }


        // ProjectDetails를 불러오는 사람이 이 프로젝트를 올린 사람인지 확인하는 로직 :: 같은 사람이 맞다면 => "isMine = true", 같은 사람이 아니면 => "isMine = false"
        // 1) 토큰 가져오기
        String token = jwtUtil.resolveToken(request);
        boolean isMine = false;
        if (token != null) {
            // 2) 토큰 유효시간 검증
            if (!jwtUtil.validateToken(token)) {
                throw new IllegalArgumentException("Token Error");
            }
            // 3) 토큰에서 사용자 정보 가져오기
            Claims claims = jwtUtil.getUserInfoFromToken(token);
            if(project.getMember().getLoginId().equals(claims.getSubject())){
                // 4) 토큰에서 전달해준 loginId와 projectId로 디비에서 찾은 프로젝트 아이디가 같다면 isMine을 true로 만든다.
                isMine = true;
            }
        }


        int projectLikeCount = projectLikeRepository.countByProject(project);
        // 이미지 데이터베이스에서 project에 연관된 thumbnailImage 리스트를 불러오기(프로젝트, 문자열인풋하기)
        List<ProjectImage> thumbnailImageList = projectImageRepository.findAllByProjectAndWhichContent(project, "thumbnailImage");
        List<String> thumbnailImageListUrl = new ArrayList<>();
        for(ProjectImage image : thumbnailImageList){
            thumbnailImageListUrl.add(image.getImageUrl());
        }


        Optional<ProjectLike> tf = projectLikeRepository.findByProject(project);
        boolean projectLike = false;
        if(tf != null) {
            projectLike = true;
        }
        ProjectDetailsReadResponseDto projectDetailsReadResponseDto = projectMapper.entityToProjectDetailsReadResponseDto(project, totalSupport, supporterCount, isMine, projectLike, projectLikeCount, thumbnailImageListUrl);
        return new ResponseDto("success", "리스트 조회 성공", projectDetailsReadResponseDto);
    }

    @Transactional
    public ResponseDto deleteProject(Long projectId, Member member) {
        // project에 저장된 댓글 전부 삭제
        Project project = projectRepository.findById(projectId).orElseThrow(
                () -> new NullPointerException("projectId로 Project를 찾을 수 없습니다.")
        );

        if (!project.getMember().getMemberId().equals(member.getMemberId())) {
            throw new IllegalArgumentException("프로젝트 진행자만 프로젝트를 삭제할 수 있습니다.");
        }

        List<Comment> commentList = commentRepository.findAllByProject(project);
        for (Comment comment : commentList) {
            commentRepository.delete(comment);
        }

        List<Support> supportList = supportRepository.findAllByProject(project);
        for (Support support : supportList) {
            supportRepository.delete(support);
        }

        projectRepository.delete(project);
        return new ResponseDto("success", "프로젝트 삭제 및 관련댓글삭제 성공", null);
    }

    @Transactional
    public ResponseDto createSupport(Member member, Long projectId, SupportCreateRequestDto supportCreateRequestDto) {

        Long memberId = member.getMemberId();

        Member memberForCreateSupport = memberRepository.findById(memberId).orElseThrow(
                () -> new NullPointerException()
        );

        Project project = projectRepository.findById(projectId).orElseThrow(
                () -> new NullPointerException()
        );

        Support support = supportMapper.toSupport(memberForCreateSupport, project, supportCreateRequestDto);

        supportRepository.save(support);

        return new ResponseDto("success", "후원 성공", null);

    }

    @Transactional
    public ResponseDto createProjectLike(Member member, Long projectId) {

        Project project = projectRepository.findById(projectId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시물 입니다.")
        );

        Optional<ProjectLike> findProjectLike = projectLikeRepository.findByProjectAndMember(project, member);

        if (findProjectLike.isPresent()) {
            projectLikeRepository.deleteByProjectAndMember(project, member);
            return new ResponseDto("success", "좋아요 취소 성공", null);
        }

        ProjectLike projectLike = projectLikeMapper.toProjectLike(member, project);
        projectLikeRepository.save(projectLike);

        return new ResponseDto("success", "좋아요 등록 성공", null);
    }
}
