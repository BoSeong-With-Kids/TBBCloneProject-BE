package com.team1.TBBCloneCoding.project.controller;

import com.team1.TBBCloneCoding.common.dto.ResponseDto;
import com.team1.TBBCloneCoding.project.dto.ProjectCreateRequestDto;
import com.team1.TBBCloneCoding.project.dto.ProjectUpdateRequestDto;
import com.team1.TBBCloneCoding.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/project")
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping("")
    public ResponseEntity<ResponseDto> createProject(@RequestBody @Valid ProjectCreateRequestDto projectCreateRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        ResponseDto responseDto = projectService.createProject(projectCreateRequestDto, userDetails.getMember());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping("/{projectId}")
    public ResponseEntity<ResponseDto> updateProject(@PathVariable Long projectId, @RequestBody @Valid ProjectUpdateRequestDto projectUpdateRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        ResponseDto responseDto = projectService.updateProject(projectId, projectUpdateRequestDto, userDetails.getMember());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<ResponseDto> getProjectList(@RequestParam("filter") String filter, @RequestParam("category") String category) {
        ResponseDto responseDto = projectService.getProjectList(filter, category);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/{projectId}/details")
    public ResponseEntity<ResponseDto> getProjectDetails(@PathVariable Long projectId,  @AuthenticationPrincipal UserDetailsImpl userDetails){
        ResponseDto responseDto = projectService.getProjectDetails(projectId, userDetails.getMember());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }



}
