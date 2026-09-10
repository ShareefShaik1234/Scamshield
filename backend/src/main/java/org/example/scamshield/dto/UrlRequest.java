package org.example.scamshield.dto;
import jakarta.validation.constraints.NotBlank;

public class UrlRequest {
    @NotBlank(message = "URL cannot be empty")

    private String url;

    public UrlRequest() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}