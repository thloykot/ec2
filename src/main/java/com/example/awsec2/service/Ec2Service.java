package com.example.awsec2.service;

import com.amazonaws.services.ec2.model.StartInstancesResult;
import com.amazonaws.services.ec2.model.StopInstancesResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface Ec2Service {

    String createInstanceUsingImage(String imageId);

    StartInstancesResult startInstance(String id);

    StopInstancesResult stopInstance(String id);

    void uploadFile(MultipartFile file) throws IOException;
}
