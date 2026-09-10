package org.example.scamshield.service;

import org.example.scamshield.dto.UrlRequest;
import org.example.scamshield.dto.UrlResponse;
import org.example.scamshield.entity.UrlScan;
import org.example.scamshield.repository.UrlScanRepository;
import org.example.scamshield.util.RiskCalculator;
import org.example.scamshield.util.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.example.scamshield.dto.VirusTotalResult;

@Service
public class UrlScanService {
    private static final Logger logger =
            LoggerFactory.getLogger(UrlScanService.class);

    @Autowired
    private UrlScanRepository repository;

    @Autowired
    private UrlValidator validator;

    @Autowired
    private RiskCalculator calculator;
    @Autowired
    private VirusTotalService virusTotalService;

    public UrlResponse scanUrl(UrlRequest request) {
        logger.info("Scanning URL: {}", request.getUrl());

        if (!validator.isValid(request.getUrl())) {
            logger.warn("Invalid URL received: {}", request.getUrl());
            return new UrlResponse(100, "INVALID", "Invalid URL format");
        }
        VirusTotalResult vtResult;

        try {
            vtResult = virusTotalService.checkUrl(request.getUrl());

            logger.info("VirusTotal Status : {}", vtResult.getStatus());
            logger.info("Malicious : {}", vtResult.getMalicious());
            logger.info("Suspicious : {}", vtResult.getSuspicious());
            logger.info("Harmless : {}", vtResult.getHarmless());

        } catch (Exception e) {
            throw new RuntimeException("VirusTotal scan failed", e);
        }

        int score = calculator.calculateRisk(request.getUrl());
        String status = calculator.getStatus(score);
        String reason = calculator.getReason(request.getUrl());

        if (vtResult.getMalicious() > 0) {
            score = 100;
            status = "DANGEROUS";
            reason = "VirusTotal detected malicious URL";
        }

        UrlScan scan = new UrlScan();
        scan.setUrl(request.getUrl());
        scan.setRiskScore(score);
        scan.setStatus(status);
        scan.setReason(reason);
        scan.setScanDate(LocalDateTime.now());

        repository.save(scan);
        logger.info("Scan completed successfully for URL: {}", request.getUrl());

        return new UrlResponse(score, status, reason);
    }

    public List<UrlScan> getAllHistory() {
        return repository.findAll();
    }

    public Optional<UrlScan> getHistoryById(Long id) {
        return repository.findById(id);
    }

    public void deleteHistory(Long id) {
        repository.deleteById(id);
    }
}