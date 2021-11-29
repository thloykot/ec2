package com.example.awsec2.service.impl;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.model.*;
import com.example.awsec2.service.Ec2Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class Ec2ServiceImpl implements Ec2Service {

    private final AmazonEC2 amazonEC2;

    @Override
    public Optional<String> createInstanceUsingImage(String imageId) {
        List<Instance> instances = amazonEC2.runInstances(buildRequest(imageId))
                .getReservation()
                .getInstances();
        if (instances.isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(instances.get(0).getInstanceId());
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

    private RunInstancesRequest buildRequest(String imageId) {
        return new RunInstancesRequest()
                .withImageId(imageId)
                .withInstanceType(InstanceType.T2Micro)
                .withMaxCount(1)
                .withMinCount(1);
    }
}
