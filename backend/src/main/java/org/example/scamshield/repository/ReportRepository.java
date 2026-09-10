package org.example.scamshield.repository;
import org.example.scamshield.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

    // Find reports by status (Pending, Resolved, Rejected)
    List<Report> findByStatus(String status);

    // Find reports by category (URL Scam, Email Scam, QR Scam, etc.)
    List<Report> findByCategory(String category);

    // Find reports submitted by a particular user
    List<Report> findByReportedBy(String reportedBy);

    // Count reports based on status (for dashboard)
    long countByStatus(String status);
}