package org.example.scamshield.controller;

import org.example.scamshield.dto.DashboardResponse;
import org.example.scamshield.entity.Report;
import org.example.scamshield.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // Dashboard Statistics
    @GetMapping("/dashboard")
    public DashboardResponse getDashboard() {
        return adminService.getDashboardStats();
    }

    // Get Pending Reports
    @GetMapping("/pending")
    public List<Report> getPendingReports() {
        return adminService.getPendingReports();
    }

    // Get Resolved Reports
    @GetMapping("/resolved")
    public List<Report> getResolvedReports() {
        return adminService.getResolvedReports();
    }

    // Get Reports By Category
    @GetMapping("/category/{category}")
    public List<Report> getReportsByCategory(@PathVariable String category) {
        return adminService.getReportsByCategory(category);
    }

    // Get Reports By User
    @GetMapping("/user/{reportedBy}")
    public List<Report> getReportsByUser(@PathVariable String reportedBy) {
        return adminService.getReportsByUser(reportedBy);
    }
}