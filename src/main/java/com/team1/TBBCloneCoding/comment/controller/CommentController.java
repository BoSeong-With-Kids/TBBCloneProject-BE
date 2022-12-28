package com.team1.TBBCloneCoding.comment.controller;


import com.team1.TBBCloneCoding.comment.dto.CommentCreateRequestDto;
import com.team1.TBBCloneCoding.comment.service.CommentService;
import com.team1.TBBCloneCoding.common.dto.ResponseDto;
import com.team1.TBBCloneCoding.member.entity.Member;
import com.team1.TBBCloneCoding.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tumblebug")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/project/{projectId}/comment")
    public ResponseEntity<ResponseDto> createComment(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, @PathVariable Long projectId, @RequestBody CommentCreateRequestDto commentCreateRequestDto) {

        ResponseDto responseDto = commentService.createComment(userDetailsImpl, projectId, commentCreateRequestDto);

        return new ResponseEntity(responseDto, HttpStatus.OK);
    }


    @GetMapping("/project/{projectId}/comment")
    public ResponseEntity<ResponseDto> getAllComment(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, @PathVariable Long projectId) {

        ResponseDto responseDto = commentService.getAllComment(userDetailsImpl, projectId);

        return new ResponseEntity(responseDto, HttpStatus.OK);
    }
    
    
    @PutMapping("/project/{projectId}/comment/{commentId}")
    public ResponseEntity<ResponseDto> updateComment(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, @PathVariable Long commentId, @RequestBody CommentCreateRequestDto commentCreateRequestDto) {

        ResponseDto responseDto = commentService.updateComment(userDetailsImpl, commentId, commentCreateRequestDto);
        
        return new ResponseEntity(responseDto, HttpStatus.OK);
     }  
     

    @DeleteMapping("/project/{projectId}/comment/{commentId}")
    public ResponseEntity<ResponseDto> deleteComment(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, @PathVariable Long commentId){

        ResponseDto responseDto = commentService.deleteComment(userDetailsImpl, commentId);

        return new ResponseEntity(responseDto, HttpStatus.OK);
    }
    
}
