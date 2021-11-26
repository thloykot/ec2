package com.example.awsec2.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.example.awsec2.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.InputStream;


@Service
@RequiredArgsConstructor
public class S3ServiceImpl implements S3Service {

    private final AmazonS3 amazonS3;

    @Override
    public void uploadFile(InputStream inputStream, String bucketname, String fileName) {
        amazonS3.putObject(bucketname, fileName, inputStream, new ObjectMetadata());

    }
}
