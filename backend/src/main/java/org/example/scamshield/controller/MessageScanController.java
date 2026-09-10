package org.example.scamshield.controller;

import jakarta.validation.Valid;
import org.example.scamshield.dto.MessageRequest;
import org.example.scamshield.dto.MessageResponse;
import org.example.scamshield.service.MessageScanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/scan")
public class MessageScanController {

    @Autowired
    private MessageScanService messageScanService;

    // Analyze Email
    @PostMapping("/email")
    public MessageResponse analyzeEmail(@Valid @RequestBody MessageRequest request) {

        return messageScanService.analyzeEmail(request);
    }

    // Analyze SMS
    @PostMapping("/sms")
    public MessageResponse analyzeSms(@Valid @RequestBody MessageRequest request) {

        return messageScanService.analyzeSms(request);
    }

    // Get All Scan History
    @GetMapping("/messages")
    public List<MessageResponse> getAllScans() {

        return messageScanService.getAllScans();
    }

    // Delete Scan History
    @DeleteMapping("/messages/{id}")
    public String deleteScan(@PathVariable Long id) {

        return messageScanService.deleteScan(id);
    }
}