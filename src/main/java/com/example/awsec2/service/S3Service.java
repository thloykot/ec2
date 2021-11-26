package com.example.awsec2.service;

import java.io.File;
import java.io.IOException;

public interface S3Service {

    void uploadFile(File file) throws IOException;
}
