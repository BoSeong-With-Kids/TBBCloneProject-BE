package com.team1.TBBCloneCoding.comment.service;

import com.team1.TBBCloneCoding.comment.dto.CommentCreateRequestDto;
import com.team1.TBBCloneCoding.comment.dto.CommentResponseDto;
import com.team1.TBBCloneCoding.comment.entity.Comment;
import com.team1.TBBCloneCoding.comment.mapper.CommentMapper;
import com.team1.TBBCloneCoding.comment.repository.CommentRepository;
import com.team1.TBBCloneCoding.common.dto.ResponseDto;
import com.team1.TBBCloneCoding.member.entity.Member;
import com.team1.TBBCloneCoding.member.repository.MemberRepository;
import com.team1.TBBCloneCoding.project.entity.Project;
import com.team1.TBBCloneCoding.project.repository.ProjectRepository;
import com.team1.TBBCloneCoding.security.UserDetailsImpl;
import com.team1.TBBCloneCoding.security.jwt.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    
    private final MemberRepository memberRepository;

    private final ProjectRepository projectRepository;

    private final CommentMapper commentMapper;

    private final JwtUtil jwtUtil;


    @Transactional
    public ResponseDto createComment(UserDetailsImpl userDetailsImpl, Long projectId, CommentCreateRequestDto commentCreateRequestDto){

        Member member = userDetailsImpl.getMember();

        Project project = projectRepository.findById(projectId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시물 입니다.")
        );

        Comment comment = commentMapper.toComment(member, commentCreateRequestDto, project);

        commentRepository.save(comment);

        return new ResponseDto("success", "댓글을 작성하였습니다", null);
    }


    @Transactional(readOnly = true)
    public ResponseDto getAllComment(HttpServletRequest request, Long projectId) {

        Project project = projectRepository.findById(projectId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시물 입니다.")
        );

        Boolean commentIsMine = false;

        String token = jwtUtil.resolveToken(request);

        List<CommentResponseDto> allCommentResponseDto = new ArrayList<>();

        List<Comment> comments = commentRepository.findAllByOrderByCreatedAtDesc();

        if (token != null) {

            Claims claims;

            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            for (Comment comment : comments) {

                if (comment.getMember().getLoginId().equals(claims.getSubject())) {
                    commentIsMine = true;
                } else {
                    commentIsMine = false;
                }
                allCommentResponseDto.add(commentMapper.toResponseDto(comment, commentIsMine));
            }
            return new ResponseDto("success", "댓글 리스트 조회 성공", allCommentResponseDto);

        }  else {
                for (Comment comment : comments) {

                    commentIsMine = false;
                    allCommentResponseDto.add(commentMapper.toResponseDto(comment, commentIsMine));
                }
            } return new ResponseDto("success", "댓글 리스트 조회 성공", allCommentResponseDto);
        }



     
     
    @Transactional
    public ResponseDto updateComment(UserDetailsImpl userDetailsImpl, Long commentId, CommentCreateRequestDto commentCreateRequestDto){

        Long memberId = userDetailsImpl.getMember().getMemberId();

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("해당 댓글 찾을 수 없습니다")
        );

        if(comment.getMember().getMemberId().equals(memberId)){
            comment.updateComment(commentCreateRequestDto.getContents());

        } else {
            throw new IllegalArgumentException("작성자만 수정할 수 있습니다");
        }

        return new ResponseDto("success", "댓글을 수정하였습니다", null);
    }
       
       
    @Transactional
    public ResponseDto deleteComment(UserDetailsImpl userDetailsImpl, Long commentId){

        Long memberId = userDetailsImpl.getMember().getMemberId();

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다.")
        );

        if(comment.getMember().getMemberId().equals(memberId)){
            commentRepository.delete(comment);
        } else {
            throw new IllegalArgumentException("작성자만 삭제할 수 있습니다");
        }

            return new ResponseDto("success", "댓글을 삭제하였습니다", null);
    }
    
}
