package com.example.study_quiz_ai.modules.ai.service.impl;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.study_quiz_ai.modules.ai.dto.PromptRequestDto;
import com.example.study_quiz_ai.modules.ai.service.GeminiService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Service
public class GeminiServiceImpl implements GeminiService {
    private static final Logger logger = LoggerFactory.getLogger(GeminiServiceImpl.class);

    @Value("${gemini.api.key}")
    private String apiKey;

    @Value("${gemini.api.url}")
    private String apiUrl;

    private final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(Duration.ofSeconds(30))
            .readTimeout(Duration.ofSeconds(60))
            .writeTimeout(Duration.ofSeconds(60))
            .build();

    private final ObjectMapper mapper = new ObjectMapper();

    public String generate(PromptRequestDto prompt) throws IOException {
        // 1. Xây dựng cấu trúc body của request
        Map<String, Object> requestBody = Map.of(
                "contents", List.of(
                        Map.of("parts", List.of(Map.of("text", prompt.getPrompt())))),
                // (Tùy chọn) Thêm các tham số để kiểm soát model

                "generationConfig", Map.of(
                        "temperature", 0.25, // Ít sáng tạo, ưu tiên chính xác
                        "topK", 30, // Giảm nhiễu
                        "topP", 0.9, // Tập trung hơn
                        "maxOutputTokens", 2048 // Cho phép câu trả lời dài hơn
                ));

        String requestBodyJson = mapper.writeValueAsString(requestBody);

        // 2. Tạo yêu cầu HTTP POST
        Request request = new Request.Builder()
                .url(apiUrl + "?key=" + apiKey)
                .post(RequestBody.create(requestBodyJson, MediaType.get("application/json; charset=utf-8")))
                .build();

        // 3. Thực thi yêu cầu và xử lý phản hồi
        try (Response response = client.newCall(request).execute()) {
            String responseBodyString = response.body() != null ? response.body().string() : "{}";

            if (!response.isSuccessful()) {
                logger.error("Gemini API Error Response: {} - {}", response.code(), responseBodyString);
                throw new IOException(
                        "Gemini API request failed with code " + response.code() + ": " + response.message());
            }

            // 4. Parse JSON response để trích xuất nội dung văn bản
            // Cấu trúc response của Gemini: { "candidates": [ { "content": { "parts": [ {
            // "text": "..." } ] } } ] }
            JsonNode rootNode = mapper.readTree(responseBodyString);
            JsonNode textNode = rootNode.path("candidates").path(0).path("content").path("parts").path(0).path("text");

            if (textNode.isMissingNode()) {
                logger.error("Could not find 'text' in Gemini response: {}", responseBodyString);
                throw new IOException("Invalid response structure from Gemini API.");
            }

            return textNode.asText();
        }
    }
}
