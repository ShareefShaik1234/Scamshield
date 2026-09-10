package org.example.scamshield.util;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class KeywordDetector {

    private static final Map<String, Integer> KEYWORDS = new HashMap<>();

    static {

        // Banking
        KEYWORDS.put("bank", 20);
        KEYWORDS.put("account", 15);
        KEYWORDS.put("otp", 20);
        KEYWORDS.put("password", 20);
        KEYWORDS.put("verify", 15);

        // Lottery
        KEYWORDS.put("winner", 15);
        KEYWORDS.put("won", 15);
        KEYWORDS.put("lottery", 20);
        KEYWORDS.put("prize", 15);
        KEYWORDS.put("gift", 10);
        KEYWORDS.put("reward", 15);

        // Urgency
        KEYWORDS.put("urgent", 15);
        KEYWORDS.put("immediately", 15);
        KEYWORDS.put("click", 10);
        KEYWORDS.put("claim", 10);
        KEYWORDS.put("limited offer", 15);

        // Money
        KEYWORDS.put("free", 10);
        KEYWORDS.put("cash", 15);
        KEYWORDS.put("salary", 10);
        KEYWORDS.put("bonus", 10);

        // Fake Job
        KEYWORDS.put("registration fee", 25);
        KEYWORDS.put("no interview", 20);
        KEYWORDS.put("work from home", 10);
    }

    public Map<String, Integer> detectKeywords(String content) {

        Map<String, Integer> detected = new LinkedHashMap<>();

        String text = content.toLowerCase();

        for (Map.Entry<String, Integer> entry : KEYWORDS.entrySet()) {

            if (text.contains(entry.getKey())) {

                detected.put(entry.getKey(), entry.getValue());

            }
        }

        return detected;
    }
}