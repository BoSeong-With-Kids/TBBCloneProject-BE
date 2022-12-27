package com.team1.TBBCloneCoding.project.controller;

import com.team1.TBBCloneCoding.common.dto.ResponseDto;
import com.team1.TBBCloneCoding.project.dto.SupportCreateRequestDto;
import com.team1.TBBCloneCoding.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Member;

@RequiredArgsConstructor
@RequestMapping("/api/tumblebug")
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping("/project/supporting/{projectId}")
    public ResponseEntity<ResponseDto> createSuppport(Member member, @PathVariable projectId, @RequestBody SupportCreateRequestDto supportCreateRequestDto){

        ResponseDto responseDto = projectService.createSupport(member, projectId, supportCreateRequestDto);

        return new ResponseEntity(responseDto, HttpStatus.OK);
    }

}
