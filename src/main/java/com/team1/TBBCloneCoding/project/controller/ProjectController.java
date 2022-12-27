package com.team1.TBBCloneCoding.project.controller;

import com.team1.TBBCloneCoding.common.dto.ResponseDto;
import com.team1.TBBCloneCoding.project.dto.ProjectCreateRequestDto;
import com.team1.TBBCloneCoding.project.dto.ProjectUpdateRequestDto;
import com.team1.TBBCloneCoding.member.entity.Member;
import com.team1.TBBCloneCoding.project.dto.SupportCreateRequestDto;
import com.team1.TBBCloneCoding.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;



@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tumblebug")
public class ProjectController {

    private final ProjectService projectService;

    @PutMapping("/{projectId}")
    public ResponseEntity<ResponseDto> updateProject(@PathVariable Long projectId, @RequestBody @Valid ProjectUpdateRequestDto projectUpdateRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        ResponseDto responseDto = projectService.updateProject(projectId, projectUpdateRequestDto, userDetails.getMember());
        return new ResponseEntity(responseDto, HttpStatus.OK);
        
    @PostMapping("/project/supporting/{projectId}")
    public ResponseEntity<ResponseDto> createSuppport(Member member, @PathVariable Long projectId, @RequestBody SupportCreateRequestDto supportCreateRequestDto){

        ResponseDto responseDto = projectService.createSupport(member, projectId, supportCreateRequestDto);

        return new ResponseEntity(responseDto, HttpStatus.OK);
    }

    @PostMapping("/project/like/{projectId}")
    public ResponseEntity<ResponseDto> createProjectLike(Member member, @PathVariable Long projectId){

        ResponseDto responseDto = projectService.createProjectLike(member, projectId);

        return new ResponseEntity(responseDto, HttpStatus.OK);
    }

    @PostMapping("/project")
    public ResponseEntity<ResponseDto> createProject(@RequestBody @Valid ProjectCreateRequestDto projectCreateRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return new ResponseEntity(responseDto, HttpStatus.OK);
        ResponseDto responseDto = projectService.createProject(projectCreateRequestDto, userDetails.getMember());
    }
}