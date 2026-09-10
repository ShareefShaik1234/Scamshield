package org.example.scamshield.service;

import org.example.scamshield.dto.JobRequest;
import org.example.scamshield.dto.JobResponse;
import org.example.scamshield.entity.JobScan;
import org.example.scamshield.repository.JobScamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobScamService {

    @Autowired
    private JobScamRepository repository;

    public JobResponse scanJob(JobRequest request) {

        int riskScore = 0;
        StringBuilder reason = new StringBuilder();

        String description = request.getJobDescription().toLowerCase();
        String email = request.getEmail().toLowerCase();
        if (request.getCompanyName() == null ||
                request.getCompanyName().trim().isEmpty()) {

            riskScore += 20;
            reason.append("Company name is missing. ");
        }

        // Rule 1
        if (description.contains("registration fee") ||
                description.contains("pay fee") ||
                description.contains("processing fee") ||
                description.contains("security deposit")) {

            riskScore += 30;
            reason.append("Registration/processing fee requested. ");
        }

        // Rule 2
        if (description.contains("guaranteed job") ||
                description.contains("100% placement")) {

            riskScore += 20;
            reason.append("Guaranteed placement promise. ");
        }

        // Rule 3
        if (description.contains("no interview")) {
            riskScore += 20;
            reason.append("No interview required. ");
        }

        // Rule 4
        if (description.contains("whatsapp")) {
            riskScore += 20;
            reason.append("WhatsApp-only contact. ");
        }

        // Rule 5
        if (description.contains("work from home")) {
            riskScore += 10;
            reason.append("Work from home offer. ");
        }

        // Rule 6
        if (request.getSalary() > 200000) {
            riskScore += 20;
            reason.append("Unusually high salary. ");
        }
        if (description.contains("no experience") &&
                request.getSalary() > 100000) {

            riskScore += 20;
            reason.append("High salary offered for no experience. ");
        }

        // Rule 7
        if (email.endsWith("@gmail.com") ||
                email.endsWith("@yahoo.com") ||
                email.endsWith("@hotmail.com") ||
                email.endsWith("@outlook.com")) {

            riskScore += 20;
            reason.append("Recruiter is using a personal email instead of an official company email. ");
        }
        if (riskScore > 100) {
            riskScore = 100;
        }

        String status;

        if (riskScore >= 60) {
            status = "DANGEROUS";
        } else if (riskScore >= 30) {
            status = "SUSPICIOUS";
        } else {
            status = "SAFE";
        }

        JobScan scan = new JobScan();
        scan.setCompanyName(request.getCompanyName());
        scan.setJobDescription(request.getJobDescription());
        scan.setEmail(request.getEmail());
        scan.setSalary(request.getSalary());
        scan.setRiskScore(riskScore);
        scan.setStatus(status);
        scan.setReason(reason.toString());

        repository.save(scan);

        return new JobResponse(riskScore, status, reason.toString());
    }
}