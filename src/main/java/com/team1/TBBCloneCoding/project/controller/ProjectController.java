package com.team1.TBBCloneCoding.project.controller;

import com.team1.TBBCloneCoding.common.dto.ResponseDto;
import com.team1.TBBCloneCoding.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/project")
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping("/list")
    public ResponseEntity<ResponseDto> getProjectList(@RequestParam("filter") String filter, @RequestParam("category") String category) {
        ResponseDto responseDto = projectService.getProjectList(filter, category);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

}
