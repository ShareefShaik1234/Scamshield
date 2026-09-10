package org.example.scamshield.dto;


import jakarta.validation.constraints.NotBlank;

public class MessageRequest {

    @NotBlank(message = "Content cannot be empty")
    private String content;

    // Default Constructor
    public MessageRequest() {
    }

    // Parameterized Constructor
    public MessageRequest(String content) {
        this.content = content;
    }

    // Getter
    public String getContent() {
        return content;
    }

    // Setter
    public void setContent(String content) {
        this.content = content;
    }
}