package org.example.scamshield.dto;

public class VirusTotalResult {

    private String status;
    private int harmless;
    private int malicious;
    private int suspicious;

    public VirusTotalResult() {
    }

    public VirusTotalResult(String status, int harmless, int malicious, int suspicious) {
        this.status = status;
        this.harmless = harmless;
        this.malicious = malicious;
        this.suspicious = suspicious;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getHarmless() {
        return harmless;
    }

    public void setHarmless(int harmless) {
        this.harmless = harmless;
    }

    public int getMalicious() {
        return malicious;
    }

    public void setMalicious(int malicious) {
        this.malicious = malicious;
    }

    public int getSuspicious() {
        return suspicious;
    }

    public void setSuspicious(int suspicious) {
        this.suspicious = suspicious;
    }
}