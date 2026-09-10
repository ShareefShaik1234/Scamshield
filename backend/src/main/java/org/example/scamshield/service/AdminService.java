package org.example.scamshield.service;
import org.example.scamshield.dto.DashboardResponse;
import org.example.scamshield.entity.Report;
import org.example.scamshield.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private ReportRepository reportRepository;

    // Dashboard Statistics
    public DashboardResponse getDashboardStats() {

        DashboardResponse dashboard = new DashboardResponse();

        dashboard.setTotalReports(reportRepository.count());
        dashboard.setPendingReports(reportRepository.countByStatus("Pending"));
        dashboard.setResolvedReports(reportRepository.countByStatus("Resolved"));
        dashboard.setRejectedReports(reportRepository.countByStatus("Rejected"));

        return dashboard;
    }

    // Get Pending Reports
    public List<Report> getPendingReports() {
        return reportRepository.findByStatus("Pending");
    }

    // Get Resolved Reports
    public List<Report> getResolvedReports() {
        return reportRepository.findByStatus("Resolved");
    }

    // Get Reports By Category
    public List<Report> getReportsByCategory(String category) {
        return reportRepository.findByCategory(category);
    }

    // Get Reports By User
    public List<Report> getReportsByUser(String reportedBy) {
        return reportRepository.findByReportedBy(reportedBy);
    }
}