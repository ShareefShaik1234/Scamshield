package org.example.scamshield.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.scamshield.dto.ScanResponse;

import java.util.List;
import java.util.Map;

@Service
public class GeminiService {

    @Value("${gemini.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public GeminiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ScanResponse analyzeEmail(String emailContent) {
        try {
            String url =
                    "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key="
                            + apiKey;

            String prompt = """
                Analyze the following email.

                Return ONLY JSON.

                {
                  "status":"SAFE or SUSPICIOUS or MALICIOUS",
                  "riskScore":0-100,
                  "reason":"short explanation"
                }

                Email:
                """ + emailContent;

            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> requestMap = Map.of(
                    "contents", List.of(
                            Map.of("parts", List.of(Map.of("text", prompt)))
                    )
            );
            String body = mapper.writeValueAsString(requestMap);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> entity = new HttpEntity<>(body, headers);

            String response = restTemplate.postForObject(url, entity, String.class);

            JsonNode root = mapper.readTree(response);

            String jsonText = root
                    .path("candidates")
                    .get(0)
                    .path("content")
                    .path("parts")
                    .get(0)
                    .path("text")
                    .asText();

            jsonText = jsonText
                    .replace("```json", "")
                    .replace("```", "")
                    .trim();

            return mapper.readValue(jsonText, ScanResponse.class);

        } catch (Exception e) {
            e.printStackTrace();
            return new ScanResponse("ERROR", 0, e.getMessage());
        }
    }
}
