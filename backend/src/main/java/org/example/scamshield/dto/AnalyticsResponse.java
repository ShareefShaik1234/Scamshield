package org.example.scamshield.dto;

public class AnalyticsResponse {

    private long totalUsers;
    private long totalUrlScans;
    private long totalJobScans;
    private long totalEmailScans;
    private long totalSmsScans;
    private long totalReports;
    private long safeScans;
    private long scamScans;

    public AnalyticsResponse() {
    }

    public AnalyticsResponse(long totalUsers, long totalUrlScans,
                             long totalJobScans, long totalEmailScans,
                             long totalSmsScans, long totalReports,
                             long safeScans, long scamScans) {
        this.totalUsers = totalUsers;
        this.totalUrlScans = totalUrlScans;
        this.totalJobScans = totalJobScans;
        this.totalEmailScans = totalEmailScans;
        this.totalSmsScans = totalSmsScans;
        this.totalReports = totalReports;
        this.safeScans = safeScans;
        this.scamScans = scamScans;
    }

    public long getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(long totalUsers) {
        this.totalUsers = totalUsers;
    }

    public long getTotalUrlScans() {
        return totalUrlScans;
    }

    public void setTotalUrlScans(long totalUrlScans) {
        this.totalUrlScans = totalUrlScans;
    }

    public long getTotalJobScans() {
        return totalJobScans;
    }

    public void setTotalJobScans(long totalJobScans) {
        this.totalJobScans = totalJobScans;
    }

    public long getTotalEmailScans() {
        return totalEmailScans;
    }

    public void setTotalEmailScans(long totalEmailScans) {
        this.totalEmailScans = totalEmailScans;
    }

    public long getTotalSmsScans() {
        return totalSmsScans;
    }

    public void setTotalSmsScans(long totalSmsScans) {
        this.totalSmsScans = totalSmsScans;
    }

    public long getTotalReports() {
        return totalReports;
    }

    public void setTotalReports(long totalReports) {
        this.totalReports = totalReports;
    }

    public long getSafeScans() {
        return safeScans;
    }

    public void setSafeScans(long safeScans) {
        this.safeScans = safeScans;
    }

    public long getScamScans() {
        return scamScans;
    }

    public void setScamScans(long scamScans) {
        this.scamScans = scamScans;
    }
}