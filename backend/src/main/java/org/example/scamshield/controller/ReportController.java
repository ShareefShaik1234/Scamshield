package org.example.scamshield.controller;

import jakarta.validation.Valid;
import org.example.scamshield.dto.ReportRequest;
import org.example.scamshield.dto.ReportResponse;
import org.example.scamshield.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    // Create Report
    @PostMapping
    public ReportResponse createReport(@Valid @RequestBody ReportRequest request) {
        return reportService.createReport(request);
    }

    // Get All Reports
    @GetMapping
    public List<ReportResponse> getAllReports() {
        return reportService.getAllReports();
    }

    // Get Report By ID
    @GetMapping("/{id}")
    public ReportResponse getReportById(@PathVariable Long id) {
        return reportService.getReportById(id);
    }

    // Update Report
    @PutMapping("/{id}")
    public ReportResponse updateReport(@PathVariable Long id,
                                       @Valid @RequestBody ReportRequest request) {
        return reportService.updateReport(id, request);
    }

    // Delete Report
    @DeleteMapping("/{id}")
    public String deleteReport(@PathVariable Long id) {
        return reportService.deleteReport(id);
    }
}