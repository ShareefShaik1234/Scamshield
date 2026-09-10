package org.example.scamshield.service;

import org.example.scamshield.dto.AnalyticsResponse;
import org.example.scamshield.repository.JobScamRepository;
import org.example.scamshield.repository.MessageScanRepository;
import org.example.scamshield.repository.ReportRepository;
import org.example.scamshield.repository.UrlScanRepository;
import org.example.scamshield.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnalyticsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UrlScanRepository urlScanRepository;

    @Autowired
    private JobScamRepository jobScamRepository;

    @Autowired
    private MessageScanRepository messageScanRepository;

    @Autowired
    private ReportRepository reportRepository;

    public AnalyticsResponse getAnalytics() {

        long totalUsers = userRepository.count();
        long totalUrlScans = urlScanRepository.count();
        long totalJobScans = jobScamRepository.count();
        long totalReports = reportRepository.count();

        // Count Email & SMS scans
        long totalEmailScans = messageScanRepository.findByMessageType("EMAIL").size();
        long totalSmsScans = messageScanRepository.findByMessageType("SMS").size();

        // Count Safe & Scam scans
        long safeScans = messageScanRepository.findByStatus("Safe").size();
        long scamScans = messageScanRepository.findByStatus("Scam").size();

        return new AnalyticsResponse(
                totalUsers,
                totalUrlScans,
                totalJobScans,
                totalEmailScans,
                totalSmsScans,
                totalReports,
                safeScans,
                scamScans
        );
    }
}