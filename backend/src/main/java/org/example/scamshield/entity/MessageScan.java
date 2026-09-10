package org.example.scamshield.entity;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "message_scan")
public class MessageScan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String messageType;   // EMAIL or SMS

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private int riskScore;

    @Column(nullable = false)
    private String status;       // Safe, Suspicious, Scam

    @Column(nullable = false)
    private String reason;

    @Column(nullable = false)
    private LocalDateTime scanDate;

    // Default Constructor
    public MessageScan() {
    }

    // Parameterized Constructor
    public MessageScan(Long id, String messageType, String content,
                       int riskScore, String status,
                       String reason, LocalDateTime scanDate) {
        this.id = id;
        this.messageType = messageType;
        this.content = content;
        this.riskScore = riskScore;
        this.status = status;
        this.reason = reason;
        this.scanDate = scanDate;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRiskScore() {
        return riskScore;
    }

    public void setRiskScore(int riskScore) {
        this.riskScore = riskScore;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDateTime getScanDate() {
        return scanDate;
    }

    public void setScanDate(LocalDateTime scanDate) {
        this.scanDate = scanDate;
    }
}