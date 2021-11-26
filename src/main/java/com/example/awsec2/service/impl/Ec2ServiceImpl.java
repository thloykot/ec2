package com.example.awsec2.service.impl;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.model.*;
import com.example.awsec2.service.Ec2Service;
import com.example.awsec2.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
public class Ec2ServiceImpl implements Ec2Service {

    private final AmazonEC2 amazonEC2;
    private final S3Service s3Service;

    @Override
    public String createInstanceUsingImage(String imageId) {
        RunInstancesRequest request = new RunInstancesRequest()
                .withImageId(imageId)
                .withInstanceType(InstanceType.T2Micro)
                .withMaxCount(1)
                .withMinCount(1);
        return amazonEC2.runInstances(request)
                .getReservation()
                .getInstances()
                .get(0)
                .getInstanceId();
    }

    @Override
    public StartInstancesResult startInstance(String id) {
        return amazonEC2.startInstances(
                new StartInstancesRequest(List.of(id))
        );
    }

    @Override
    public StopInstancesResult stopInstance(String id) {
        return amazonEC2.stopInstances(
                new StopInstancesRequest(List.of(id))
        );
    }

    @Override
    public void uploadFile(MultipartFile file) throws IOException {
        File temp = new File("src/main/resources/Geometry.jar");
        try(OutputStream outputStream = new FileOutputStream(temp)){
            outputStream.write(file.getBytes());
            s3Service.uploadFile(temp);
        }finally {
            temp.delete();
        }
    }
}
