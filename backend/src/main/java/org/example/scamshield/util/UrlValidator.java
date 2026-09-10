package org.example.scamshield.util;

import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URL;

@Component
public class UrlValidator {

    public boolean isValid(String url) {

        if (url == null || url.trim().isEmpty()) {
            return false;
        }

        try {
            URL parsedUrl = URI.create(url).toURL();

            String protocol = parsedUrl.getProtocol();

            return protocol.equals("http") || protocol.equals("https");

        } catch (Exception e) {
            return false;
        }
    }
}