package org.example.scamshield.dto;

public class UrlResponse {

    private int riskScore;
    private String status;
    private String reason;

    public UrlResponse() {
    }

    public UrlResponse(int riskScore, String status, String reason) {
        this.riskScore = riskScore;
        this.status = status;
        this.reason = reason;
    }

    public int getRiskScore() {
        return riskScore;
    }

    public void setRiskScore(int riskScore) {
        this.riskScore = riskScore;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}