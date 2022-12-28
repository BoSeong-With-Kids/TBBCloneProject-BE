package com.team1.TBBCloneCoding.s3;

import com.team1.TBBCloneCoding.common.dto.ResponseDto;
import com.team1.TBBCloneCoding.project.dto.ProjectImageDeleteRequestDto;
import com.team1.TBBCloneCoding.project.dto.ProjectImageResponseDto;
import com.team1.TBBCloneCoding.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tumblebug")
public class S3ApiController {
    private final S3ApiService s3ApiService;

    @PostMapping("/project/image")
    public ResponseEntity<ResponseDto> upload(@AuthenticationPrincipal UserDetailsImpl userDetails, MultipartHttpServletRequest multipartFile) throws IOException {
        MultipartFile file = multipartFile.getFile("upload");
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        ResponseDto responseDto = s3ApiService.upload(file, fileName, "temp/" + userDetails.getMember().getMemberId().toString() + "/");
        return new ResponseEntity(responseDto, HttpStatus.OK);
    }

    @PostMapping("/project/thumbnail")
    public ResponseEntity<ResponseDto> thumbnailUpload(@AuthenticationPrincipal UserDetailsImpl userDetails, MultipartHttpServletRequest multipartFile) throws IOException {
        MultipartFile file = multipartFile.getFile("upload");
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        ResponseDto responseDto = s3ApiService.upload(file, fileName, "temp/" + userDetails.getMember().getMemberId().toString() + "/");
        return new ResponseEntity(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/project/image")
    public ResponseEntity<ResponseDto> delete(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody ProjectImageDeleteRequestDto projectImageDeleteRequestDto){
        ResponseDto responseDto = s3ApiService.delete(userDetails.getMember(), projectImageDeleteRequestDto.getImagePk());
        return new ResponseEntity(responseDto, HttpStatus.OK);
    }
}

