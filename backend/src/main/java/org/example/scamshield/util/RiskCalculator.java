package org.example.scamshield.util;

import org.springframework.stereotype.Component;
import java.util.regex.Pattern;

@Component
public class RiskCalculator {

    public int calculateRisk(String url) {

        int score = 0;
        String lowerUrl = url.toLowerCase();
        if (isTrustedDomain(lowerUrl)) {
            return 0;
        }

        // No HTTPS
        if (!lowerUrl.startsWith("https://")) {
            score += 20;
        }

        // Contains @
        if (lowerUrl.contains("@")) {
            score += 20;
        }

        // Suspicious keywords
        String[] keywords = {
                "login", "verify", "update", "bank",
                "password", "otp", "gift", "free",
                "reward", "wallet", "payment"
        };

        for (String keyword : keywords) {
            if (lowerUrl.contains(keyword)) {
                score += 10;
            }
        }

        // Shortened URLs
        if (lowerUrl.contains("bit.ly") ||
                lowerUrl.contains("tinyurl") ||
                lowerUrl.contains("goo.gl")) {

            score += 20;
        }

        // Suspicious domains
        if (lowerUrl.endsWith(".xyz") ||
                lowerUrl.endsWith(".top") ||
                lowerUrl.endsWith(".click")) {

            score += 20;
        }

        // Very long URL
        if (lowerUrl.length() > 100) {
            score += 10;
        }

        // Too many hyphens
        int hyphenCount = lowerUrl.length() - lowerUrl.replace("-", "").length();

        if (hyphenCount >= 3) {
            score += 15;
        }
        if (isIPAddress(lowerUrl)) {
            score += 30;
        }



        return Math.min(score, 100);

    }

    public String getStatus(int score) {

        if (score <= 20)
            return "SAFE";

        if (score <= 40)
            return "LOW";

        if (score <= 60)
            return "MEDIUM";

        if (score <= 80)
            return "HIGH";

        return "DANGEROUS";
    }

    public String getReason(String url) {

        StringBuilder reason = new StringBuilder();
        String lowerUrl = url.toLowerCase();
        if (isTrustedDomain(lowerUrl)) {
            return "Trusted domain";
        }

        if (!lowerUrl.startsWith("https://"))
            reason.append("No HTTPS, ");

        if (lowerUrl.contains("@"))
            reason.append("Contains @, ");

        if (lowerUrl.contains("login"))
            reason.append("Contains login, ");

        if (lowerUrl.contains("verify"))
            reason.append("Contains verify, ");

        if (lowerUrl.contains("update"))
            reason.append("Contains update, ");

        if (lowerUrl.contains("bank"))
            reason.append("Contains bank, ");

        if (lowerUrl.contains("password"))
            reason.append("Contains password, ");

        if (lowerUrl.contains("otp"))
            reason.append("Contains OTP, ");

        if (lowerUrl.contains("gift"))
            reason.append("Contains gift, ");

        if (lowerUrl.contains("free"))
            reason.append("Contains free, ");

        if (lowerUrl.contains("reward"))
            reason.append("Contains reward, ");

        if (lowerUrl.contains("wallet"))
            reason.append("Contains wallet, ");

        if (lowerUrl.contains("payment"))
            reason.append("Contains payment, ");

        if (lowerUrl.contains("bit.ly") ||
                lowerUrl.contains("tinyurl") ||
                lowerUrl.contains("goo.gl"))
            reason.append("Shortened URL, ");

        if (lowerUrl.endsWith(".xyz") ||
                lowerUrl.endsWith(".top") ||
                lowerUrl.endsWith(".click"))
            reason.append("Suspicious domain, ");

        if (lowerUrl.length() > 100)
            reason.append("Very long URL, ");

        int hyphenCount = lowerUrl.length() - lowerUrl.replace("-", "").length();

        if (hyphenCount >= 3)
            reason.append("Too many hyphens, ");
        if (isIPAddress(lowerUrl))
            reason.append("Uses IP address instead of domain, ");

        if (reason.length() == 0)
            return "No suspicious patterns found.";

        return reason.substring(0, reason.length() - 2);
    }
    private boolean isIPAddress(String url) {

        String regex = "^(http://|https://)?(\\d{1,3}\\.){3}\\d{1,3}.*$";

        return Pattern.matches(regex, url);
    }
    private boolean isTrustedDomain(String url) {

        String[] trustedDomains = {
                "google.com",
                "microsoft.com",
                "github.com",
                "amazon.com",
                "openai.com",
                "oracle.com",
                "spring.io"
        };

        for (String domain : trustedDomains) {
            if (url.contains(domain)) {
                return true;
            }
        }

        return false;
    }

}