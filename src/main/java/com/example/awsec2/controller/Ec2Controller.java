package com.example.awsec2.controller;

import com.amazonaws.services.ec2.model.StartInstancesResult;
import com.amazonaws.services.ec2.model.StopInstancesResult;
import com.example.awsec2.service.Ec2Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequiredArgsConstructor
@RequestMapping("instance")
public class Ec2Controller {

    private final Ec2Service ec2Service;

    @PostMapping("/create/{imageId}")
    public ResponseEntity<String> createInstanceUsingImage(@PathVariable("imageId") String id) {
        return ResponseEntity.ok(ec2Service.createInstanceUsingImage(id));
    }

    @GetMapping("/stop/{id}")
    public ResponseEntity<StopInstancesResult> stopInstance(@PathVariable("id") String id) {
        return ResponseEntity.ok(ec2Service.stopInstance(id));
    }

    @GetMapping("/start/{id}")
    public ResponseEntity<StartInstancesResult> startInstance(@PathVariable("id") String id) {
        return ResponseEntity.ok(ec2Service.startInstance(id));
    }

    @PostMapping(value = "/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
        try {
            ec2Service.uploadFile(file);
            return ResponseEntity.ok("File has been uploaded");
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
