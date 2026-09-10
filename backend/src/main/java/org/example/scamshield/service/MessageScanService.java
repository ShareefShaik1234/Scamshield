package org.example.scamshield.service;

import org.example.scamshield.dto.MessageRequest;
import org.example.scamshield.dto.MessageResponse;
import org.example.scamshield.entity.MessageScan;
import org.example.scamshield.exception.ResourceNotFoundException;
import org.example.scamshield.repository.MessageScanRepository;
import org.example.scamshield.util.MessageAnalyzer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageScanService {

    @Autowired
    private MessageScanRepository messageScanRepository;

    @Autowired
    private MessageAnalyzer messageAnalyzer;

    // Analyze Email
    public MessageResponse analyzeEmail(MessageRequest request) {

        return analyzeMessage(request, "EMAIL");
    }

    // Analyze SMS
    public MessageResponse analyzeSms(MessageRequest request) {

        return analyzeMessage(request, "SMS");
    }

    // Common Analysis Method
    private MessageResponse analyzeMessage(MessageRequest request, String messageType) {

        MessageAnalyzer.AnalysisResult result =
                messageAnalyzer.analyze(request.getContent());

        MessageScan scan = new MessageScan();

        scan.setMessageType(messageType);
        scan.setContent(request.getContent());
        scan.setRiskScore(result.getRiskScore());
        scan.setStatus(result.getStatus());
        scan.setReason(result.getReason());
        scan.setScanDate(LocalDateTime.now());

        MessageScan savedScan = messageScanRepository.save(scan);

        return mapToResponse(savedScan);
    }

    // Get All Scan History
    public List<MessageResponse> getAllScans() {

        return messageScanRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Delete Scan
    public String deleteScan(Long id) {

        if (!messageScanRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Scan record not found with ID: " + id);
        }

        messageScanRepository.deleteById(id);

        return "Scan history deleted successfully.";
    }

    // Convert Entity to DTO
    private MessageResponse mapToResponse(MessageScan scan) {

        MessageResponse response = new MessageResponse();

        response.setId(scan.getId());
        response.setMessageType(scan.getMessageType());
        response.setContent(scan.getContent());
        response.setRiskScore(scan.getRiskScore());
        response.setStatus(scan.getStatus());
        response.setReason(scan.getReason());
        response.setScanDate(scan.getScanDate());

        return response;
    }
}