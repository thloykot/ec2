package com.example.awsec2.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.example.awsec2.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;


@Service
@RequiredArgsConstructor
public class S3ServiceImpl implements S3Service {

    private final AmazonS3 amazonS3;

    private static final String BUCKET_NAME = "test-bucket-geo";

    @Override
    public void uploadFile(File file) {
            amazonS3.putObject(BUCKET_NAME, file.getName(), file);

    }
}
