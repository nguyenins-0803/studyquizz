package com.example.study_quiz_ai.modules.ai.service.impl;

import com.example.study_quiz_ai.modules.ai.service.GeminiService;

import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Service;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

@Service
public class GeminiServiceImpl implements GeminiService {

    @Override
    public String testGemini(String param) {
        Client client = Client.builder()
                .apiKey("AIzaSyCP2BKfgWLQPhVXav-ixvZjBdUydCm3twc")
                .build();

        GenerateContentResponse response = client.models.generateContent("gemini-2.5-flash",
                param, null);

        return new String(response.text().getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
    }

}
