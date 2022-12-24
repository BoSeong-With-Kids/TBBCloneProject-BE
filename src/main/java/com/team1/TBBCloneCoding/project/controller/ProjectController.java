package com.team1.TBBCloneCoding.project.controller;

import com.team1.TBBCloneCoding.common.dto.ResponseDto;
import com.team1.TBBCloneCoding.project.dto.ProjectCreateRequestDto;
import com.team1.TBBCloneCoding.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

}
