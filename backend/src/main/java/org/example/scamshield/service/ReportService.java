package org.example.scamshield.service;

import org.example.scamshield.dto.ReportRequest;
import org.example.scamshield.dto.ReportResponse;
import org.example.scamshield.entity.Report;
import org.example.scamshield.exception.ResourceNotFoundException;
import org.example.scamshield.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    // Create Report
    public ReportResponse createReport(ReportRequest request) {

        Report report = new Report();

        report.setUrl(request.getUrl());
        report.setDescription(request.getDescription());
        report.setCategory(request.getCategory());
        report.setReportedBy(request.getReportedBy());

        // Default Values
        report.setStatus("Pending");
        report.setReportedDate(LocalDateTime.now());

        Report savedReport = reportRepository.save(report);

        return mapToResponse(savedReport);
    }

    // Get All Reports
    public List<ReportResponse> getAllReports() {

        return reportRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Get Report By ID
    public ReportResponse getReportById(Long id) {

        Report report = reportRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Report not found with ID: " + id));

        return mapToResponse(report);
    }

    // Update Report
    public ReportResponse updateReport(Long id, ReportRequest request) {

        Report report = reportRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Report not found with ID: " + id));

        report.setUrl(request.getUrl());
        report.setDescription(request.getDescription());
        report.setCategory(request.getCategory());
        report.setReportedBy(request.getReportedBy());

        Report updatedReport = reportRepository.save(report);

        return mapToResponse(updatedReport);
    }

    // Delete Report
    public String deleteReport(Long id) {

        if (!reportRepository.existsById(id)) {
            throw new ResourceNotFoundException("Report not found with ID: " + id);
        }

        reportRepository.deleteById(id);

        return "Report deleted successfully.";
    }

    // Convert Entity to Response DTO
    private ReportResponse mapToResponse(Report report) {

        ReportResponse response = new ReportResponse();

        response.setId(report.getId());
        response.setUrl(report.getUrl());
        response.setDescription(report.getDescription());
        response.setCategory(report.getCategory());
        response.setStatus(report.getStatus());
        response.setReportedBy(report.getReportedBy());
        response.setReportedDate(report.getReportedDate());

        return response;
    }
}