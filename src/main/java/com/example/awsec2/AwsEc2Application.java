package com.example.awsec2;

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AwsEc2Application {

    @Bean
    EnvironmentVariableCredentialsProvider credentialsProvider() {
        return new EnvironmentVariableCredentialsProvider();
    }

    @Bean
    AmazonEC2 amazonEC2(EnvironmentVariableCredentialsProvider credentialsProvider) {
        return AmazonEC2Client.builder()
                .withRegion(Regions.US_EAST_1)
                .withCredentials(credentialsProvider)
                .build();
    }

    @Bean
    AmazonS3 amazonS3(EnvironmentVariableCredentialsProvider credentialsProvider) {
        return AmazonS3Client.builder()
                .withRegion(Regions.US_EAST_1)
                .withCredentials(credentialsProvider)
                .build();
    }

    public static void main(String[] args) {
        SpringApplication.run(AwsEc2Application.class, args);

    }

}