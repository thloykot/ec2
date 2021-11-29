package com.example.awsec2.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public interface S3Service {

    void uploadFile(InputStream inputStream, String bucketName, String fileName) throws IOException;
}
