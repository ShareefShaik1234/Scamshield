package org.example.scamshield.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.scamshield.dto.VirusTotalResult;

@Service
public class VirusTotalService {

    @Value("${virustotal.api.key}")
    private String apiKey;

    @Value("${virustotal.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;

    public VirusTotalService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public String submitUrl(String url) {
        System.out.println("URL Received: " + url);

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-apikey", apiKey);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("url", url);

        HttpEntity<MultiValueMap<String, String>> request =
                new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                apiUrl + "/urls",
                HttpMethod.POST,
                request,
                String.class
        );

        return response.getBody();
    }
    public String getAnalysisResult(String analysisId) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-apikey", apiKey);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                apiUrl + "/analyses/" + analysisId,
                HttpMethod.GET,
                entity,
                String.class
        );

        return response.getBody();
    }
    public VirusTotalResult checkUrl(String url) throws Exception {

        String submitResponse = submitUrl(url);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode submitNode = mapper.readTree(submitResponse);

        String analysisId = submitNode.get("data").get("id").asText();

        Thread.sleep(3000);

        String analysisResponse = getAnalysisResult(analysisId);

        JsonNode analysisNode = mapper.readTree(analysisResponse);

        JsonNode attributes = analysisNode.get("data").get("attributes");

        String status = attributes.get("status").asText();

        JsonNode stats = attributes.get("stats");

        return new VirusTotalResult(
                status,
                stats.get("harmless").asInt(),
                stats.get("malicious").asInt(),
                stats.get("suspicious").asInt()
        );
    }


}