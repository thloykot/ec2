package com.example.awsec2.controller;

import com.example.awsec2.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("S3")
public class S3Controller {

    private final S3Service s3Service;

    @PostMapping(value = "/upload/{bucket-name}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file
            , @PathVariable("bucket-name") String bucketName) {
        try {
            s3Service.uploadFile(file.getInputStream(), bucketName, file.getOriginalFilename());
            return ResponseEntity.ok("File has been uploaded");
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
