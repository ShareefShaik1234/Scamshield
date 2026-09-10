package org.example.scamshield.dto;

import java.time.LocalDateTime;

public class ReportResponse {

    private Long id;

    private String url;

    private String description;

    private String category;

    private String status;

    private String reportedBy;

    private LocalDateTime reportedDate;

    // Default Constructor
    public ReportResponse() {
    }

    // Parameterized Constructor
    public ReportResponse(Long id, String url, String description,
                          String category, String status,
                          String reportedBy, LocalDateTime reportedDate) {
        this.id = id;
        this.url = url;
        this.description = description;
        this.category = category;
        this.status = status;
        this.reportedBy = reportedBy;
        this.reportedDate = reportedDate;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReportedBy() {
        return reportedBy;
    }

    public void setReportedBy(String reportedBy) {
        this.reportedBy = reportedBy;
    }

    public LocalDateTime getReportedDate() {
        return reportedDate;
    }

    public void setReportedDate(LocalDateTime reportedDate) {
        this.reportedDate = reportedDate;
    }
}