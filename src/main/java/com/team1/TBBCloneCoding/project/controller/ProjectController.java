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

    @PutMapping("/{projectId}")
    public ResponseEntity<ResponseDto> updateProject(@PathVariable Long projectId, @RequestBody @Valid ProjectUpdateRequestDto projectUpdateRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        ResponseDto responseDto = projectService.updateProject(projectId, projectUpdateRequestDto, userDetails.getMember());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

}
