package org.example.scamshield.controller;

import org.example.scamshield.dto.JobRequest;
import org.example.scamshield.dto.JobResponse;
import org.example.scamshield.service.JobScamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/job")
@CrossOrigin(origins = "*")
public class JobScamController {

    @Autowired
    private JobScamService jobScamService;

    @PostMapping("/scan")
    public JobResponse scanJob(@RequestBody JobRequest request) {
        return jobScamService.scanJob(request);
    }
}