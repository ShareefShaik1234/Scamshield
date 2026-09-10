package org.example.scamshield.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.scamshield.dto.UrlRequest;
import org.example.scamshield.dto.UrlResponse;
import org.example.scamshield.entity.UrlScan;
import org.example.scamshield.service.UrlScanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.example.scamshield.service.VirusTotalService;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/scan")
@Tag(name = "URL Scanner API", description = "APIs for detecting phishing and scam URLs")
public class UrlScanController {

    @Autowired
    private UrlScanService service;
    @Autowired
    private VirusTotalService virusTotalService;


    @Operation(summary = "Scan a URL", description = "Checks whether a URL is safe or suspicious")
    @PostMapping("/url")
    public UrlResponse scanUrl(@Valid @RequestBody UrlRequest request) {

        System.out.println("URL received: " + request.getUrl());

        return service.scanUrl(request);
    }
    @Operation(summary = "View Scan History", description = "Returns all previously scanned URLs")
    @GetMapping("/history")
    public List<UrlScan> getHistory() {
        return service.getAllHistory();
    }

    @GetMapping("/history/{id}")
    public Optional<UrlScan> getHistoryById(@PathVariable Long id) {
        return service.getHistoryById(id);
    }
    @Operation(summary = "Delete Scan History", description = "Deletes a scanned URL by ID")
    @DeleteMapping("/history/{id}")
    public String deleteHistory(@PathVariable Long id) {
        service.deleteHistory(id);
        return "History deleted successfully.";
    }
    @PostMapping("/test-virus")
    public String testVirusTotal(@RequestParam String url) throws Exception {

        String submitResponse = virusTotalService.submitUrl(url);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(submitResponse);

        String analysisId = node.get("data").get("id").asText();
        Thread.sleep(3000);

        return virusTotalService.getAnalysisResult(analysisId);
    }
}