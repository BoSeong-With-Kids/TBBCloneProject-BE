package com.team1.TBBCloneCoding.project.controller;

import com.team1.TBBCloneCoding.common.dto.ResponseDto;
import com.team1.TBBCloneCoding.project.dto.ProjectCreateRequestDto;
import com.team1.TBBCloneCoding.project.dto.ProjectUpdateRequestDto;
import com.team1.TBBCloneCoding.member.entity.Member;
import com.team1.TBBCloneCoding.project.dto.SupportCreateRequestDto;
import com.team1.TBBCloneCoding.project.service.ProjectService;
import com.team1.TBBCloneCoding.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tumblebug")
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping("/project")
    public ResponseEntity<ResponseDto> createProject(@RequestBody @Valid ProjectCreateRequestDto projectCreateRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        ResponseDto responseDto = projectService.createProject(projectCreateRequestDto, userDetails.getMember());
        return new ResponseEntity(responseDto, HttpStatus.OK);
    }

    @GetMapping("/project/list")
    public ResponseEntity<ResponseDto> getProjectList(@RequestParam("filter") String filter, @RequestParam("category") String category) {
        ResponseDto responseDto = projectService.getProjectList(filter, category);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/project/{projectId}/details")
    public ResponseEntity<ResponseDto> getProjectDetails(@PathVariable Long projectId, HttpServletRequest request){
        ResponseDto responseDto = projectService.getProjectDetails(projectId, request);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping("/project/{projectId}")
    public ResponseEntity<ResponseDto> updateProject(@PathVariable Long projectId, @RequestBody @Valid ProjectUpdateRequestDto projectUpdateRequestDto, HttpServletRequest request){
        ResponseDto responseDto = projectService.updateProject(projectId, projectUpdateRequestDto, request);
        return new ResponseEntity(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/project/{projectId}")
    public ResponseEntity<ResponseDto> deleteProject(@PathVariable Long projectId){
        ResponseDto responseDto = projectService.deleteProject(projectId);
        return new ResponseEntity(responseDto,HttpStatus.OK);
    }

    @PostMapping("/project/supporting/{projectId}")
    public ResponseEntity<ResponseDto> createSupport(Member member, @PathVariable Long projectId, @RequestBody SupportCreateRequestDto supportCreateRequestDto){
        ResponseDto responseDto = projectService.createSupport(member, projectId, supportCreateRequestDto);
        return new ResponseEntity(responseDto, HttpStatus.OK);
    }

    @PostMapping("/project/like/{projectId}")
    public ResponseEntity<ResponseDto> createProjectLike(Member member, @PathVariable Long projectId){
        ResponseDto responseDto = projectService.createProjectLike(member, projectId);
        return new ResponseEntity(responseDto, HttpStatus.OK);
    }
}
