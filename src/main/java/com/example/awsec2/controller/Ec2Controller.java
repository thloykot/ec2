package com.example.awsec2.controller;

import com.amazonaws.services.ec2.model.StartInstancesResult;
import com.amazonaws.services.ec2.model.StopInstancesResult;
import com.example.awsec2.service.Ec2Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("instance")
public class Ec2Controller {

    private final Ec2Service ec2Service;

    @PostMapping("/create/{imageId}")
    public ResponseEntity<String> createInstanceUsingImage(@PathVariable("imageId") String id) {
        return ec2Service.createInstanceUsingImage(id)
                .map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/stop/{id}")
    public ResponseEntity<StopInstancesResult> stopInstance(@PathVariable("id") String id) {
        return ResponseEntity.ok(ec2Service.stopInstance(id));
    }

    @GetMapping("/start/{id}")
    public ResponseEntity<StartInstancesResult> startInstance(@PathVariable("id") String id) {
        return ResponseEntity.ok(ec2Service.startInstance(id));
    }


}
