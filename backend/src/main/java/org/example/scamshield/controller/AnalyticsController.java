package org.example.scamshield.controller;

import org.example.scamshield.dto.AnalyticsResponse;
import org.example.scamshield.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class AnalyticsController {

    @Autowired
    private AnalyticsService analyticsService;

    @GetMapping("/analytics")
    public AnalyticsResponse getAnalytics() {
        return analyticsService.getAnalytics();
    }
}