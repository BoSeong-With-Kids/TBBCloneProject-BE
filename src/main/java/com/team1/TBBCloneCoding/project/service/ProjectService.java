package com.team1.TBBCloneCoding.project.service;

import com.team1.TBBCloneCoding.comment.entity.Comment;
import com.team1.TBBCloneCoding.comment.repository.CommentRepository;
import com.team1.TBBCloneCoding.common.dto.ResponseDto;
import com.team1.TBBCloneCoding.member.entity.Member;
import com.team1.TBBCloneCoding.project.entity.Project;
import com.team1.TBBCloneCoding.project.entity.Support;
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
    private final CommentRepository commentRepository;

    public ResponseDto deleteProject(Long projectId) {
        // project에 저장된 댓글 전부 삭제
        Project project = projectRepository.findById(projectId).orElseThrow(
                () -> new NullPointerException("projectId로 Project를 찾을 수 없습니다.")
        );

        List<Comment> commentList = commentRepository.findAllByProject(project);
        for(Comment comment : commentList) {
            commentRepository.delete(comment);
        }

        List<Support> supportList = supportRepository.findAllByProject(project);
        for(Support support : supportList){
            supportRepository.delete(support);
        }

        return new ResponseDto("success", "프로젝트 삭제 및 관련댓글삭제 성공",null);
    }
}
