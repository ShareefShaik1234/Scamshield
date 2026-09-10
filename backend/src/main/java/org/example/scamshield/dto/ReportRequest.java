package org.example.scamshield.dto;

import jakarta.validation.constraints.NotBlank;

public class ReportRequest {

    @NotBlank(message = "URL is required")
    private String url;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Category is required")
    private String category;

    @NotBlank(message = "Reported By is required")
    private String reportedBy;

    // Default Constructor
    public ReportRequest() {
    }

    // Parameterized Constructor
    public ReportRequest(String url, String description, String category, String reportedBy) {
        this.url = url;
        this.description = description;
        this.category = category;
        this.reportedBy = reportedBy;
    }

    // Getters and Setters

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

    public String getReportedBy() {
        return reportedBy;
    }

    public void setReportedBy(String reportedBy) {
        this.reportedBy = reportedBy;
    }
}