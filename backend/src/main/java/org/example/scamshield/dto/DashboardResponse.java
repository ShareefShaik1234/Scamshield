package org.example.scamshield.dto;

public class DashboardResponse {

    private long totalReports;
    private long pendingReports;
    private long resolvedReports;
    private long rejectedReports;

    // Default Constructor
    public DashboardResponse() {
    }

    // Parameterized Constructor
    public DashboardResponse(long totalReports, long pendingReports,
                             long resolvedReports, long rejectedReports) {
        this.totalReports = totalReports;
        this.pendingReports = pendingReports;
        this.resolvedReports = resolvedReports;
        this.rejectedReports = rejectedReports;
    }

    // Getters and Setters

    public long getTotalReports() {
        return totalReports;
    }

    public void setTotalReports(long totalReports) {
        this.totalReports = totalReports;
    }

    public long getPendingReports() {
        return pendingReports;
    }

    public void setPendingReports(long pendingReports) {
        this.pendingReports = pendingReports;
    }

    public long getResolvedReports() {
        return resolvedReports;
    }

    public void setResolvedReports(long resolvedReports) {
        this.resolvedReports = resolvedReports;
    }

    public long getRejectedReports() {
        return rejectedReports;
    }

    public void setRejectedReports(long rejectedReports) {
        this.rejectedReports = rejectedReports;
    }
}