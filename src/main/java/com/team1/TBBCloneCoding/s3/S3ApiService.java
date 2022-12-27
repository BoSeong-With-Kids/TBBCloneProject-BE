package com.team1.TBBCloneCoding.s3;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.team1.TBBCloneCoding.common.dto.ResponseDto;
import com.team1.TBBCloneCoding.member.entity.Member;
import com.team1.TBBCloneCoding.project.dto.ProjectImageResponseDto;
import com.team1.TBBCloneCoding.project.entity.ProjectImage;
import com.team1.TBBCloneCoding.project.mapper.ProjectImageMapper;
import com.team1.TBBCloneCoding.project.repository.ProjectImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class S3ApiService {
    private AmazonS3 s3Client;
    private final ProjectImageRepository projectImageReposirory;
    private final ProjectImageMapper projectImageMapper;

    @Value("${cloud.aws.credentials.accessKey}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secretKey}")
    private String secretKey;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.region.static}")
    private String region;


    @PostConstruct
    public void setS3Client() {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);

        s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(this.region)
                .build();
    }

    public ResponseDto upload(MultipartFile multipartFile, String fileName, String dirName) throws IOException {
        File uploadFile = convert(multipartFile).orElseThrow(() -> new IllegalArgumentException("파일변환실패"));
        fileName = dirName + fileName;
        String uploadImageUrl = putS3(uploadFile, fileName);
        removeNewFile(uploadFile);
        // s3 url을 이미지 디비에 저장하고 ( uploadImageUrl받아서 매퍼 통해서 ProjectImage 객체생성)
        // pk값, url을 반환하면됨

        // 저장
        ProjectImage projectImage = projectImageMapper.entityToProjectImage(uploadImageUrl);
        projectImageReposirory.save(projectImage);

        // pk
        Long returnPk = projectImageReposirory.findByImageUrl(uploadImageUrl).getImageId();
        ProjectImageResponseDto projectImageResponseDto = projectImageMapper.toResponseDto(returnPk,uploadImageUrl);
        return new ResponseDto("success", "사진이 s3에 임시로 업로드 되었습니다.", projectImageResponseDto);
    }

    // 로컬에 있는 파일제거
    private void removeNewFile(File targetFile) {
        if(targetFile.delete()) {
            System.out.println("파일이 삭제되었습니다.");
        }else {
            System.out.println("파일이 삭제되지 못했습니다.");
        }
    }

    private Optional<File> convert(MultipartFile file) throws  IOException {
        File convertFile = new File(file.getOriginalFilename());
        if(convertFile.createNewFile()) {
            try (FileOutputStream fos = new FileOutputStream(convertFile)) {
                fos.write(file.getBytes());
            }
            return Optional.of(convertFile);
        }
        return Optional.empty();
    }
    private String putS3(File uploadFile, String fileName) {
        s3Client.putObject(
                new PutObjectRequest(bucket, fileName, uploadFile)
                        .withCannedAcl(CannedAccessControlList.PublicRead)
        );
        return s3Client.getUrl(bucket, fileName).toString();
    }


    // s3내에서 이미지 위치 변경 코드
    public void update(String oldSource, String newSource) {
        try {
            oldSource = URLDecoder.decode(oldSource, "UTF-8");
            newSource = URLDecoder.decode(newSource, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        moveS3(oldSource, newSource);
        deleteS3(oldSource);
    }

    private void moveS3(String oldSource, String newSource) {
        s3Client.copyObject(bucket, oldSource, bucket, newSource);
    }

    private void deleteS3(String source) {
        s3Client.deleteObject(bucket, source);
    }

    @Transactional
    public ResponseDto delete(Member member, List<Long> imagePk) {
        for(Long id : imagePk) {
            ProjectImage projectImage = projectImageReposirory.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("프로젝트 이미지가 존재하지 않습니다.")
            );
            projectImageReposirory.deleteById(projectImage.getImageId());
            deleteS3(projectImage.getImageUrl());
        }
        return new ResponseDto("success","사진 삭제를 완료했습니다.", null);
    }
}
