package org.example.scamshield.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MessageAnalyzer {

    @Autowired
    private KeywordDetector keywordDetector;

    public AnalysisResult analyze(String content) {

        Map<String, Integer> keywords =
                keywordDetector.detectKeywords(content);

        int riskScore = 0;

        StringBuilder reason = new StringBuilder();

        for (Map.Entry<String, Integer> entry : keywords.entrySet()) {

            riskScore += entry.getValue();

            reason.append(entry.getKey()).append(", ");

        }

        // URL Detection
        if (content.toLowerCase().contains("http://")
                || content.toLowerCase().contains("https://")) {

            riskScore += 20;

            reason.append("URL detected, ");

        }

        // Currency Detection
        if (content.contains("₹")
                || content.toLowerCase().contains("lakh")
                || content.toLowerCase().contains("crore")) {

            riskScore += 15;

            reason.append("Money amount detected, ");

        }

        // Too many exclamation marks
        if (content.contains("!!!")) {

            riskScore += 10;

            reason.append("Multiple exclamation marks, ");

        }

        // Capital Letters
        long capitalLetters =
                content.chars().filter(Character::isUpperCase).count();

        if (capitalLetters > 10) {

            riskScore += 10;

            reason.append("Too many capital letters, ");

        }

        if (riskScore > 100)
            riskScore = 100;

        String status;

        if (riskScore >= 70)
            status = "Scam";
        else if (riskScore >= 40)
            status = "Suspicious";
        else
            status = "Safe";

        if (reason.length() == 0)
            reason.append("No suspicious pattern detected.");

        return new AnalysisResult(riskScore, status, reason.toString());

    }

    public static class AnalysisResult {

        private int riskScore;
        private String status;
        private String reason;

        public AnalysisResult(int riskScore,
                              String status,
                              String reason) {

            this.riskScore = riskScore;
            this.status = status;
            this.reason = reason;
        }

        public int getRiskScore() {
            return riskScore;
        }

        public String getStatus() {
            return status;
        }

        public String getReason() {
            return reason;
        }
    }
}