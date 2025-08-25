package com.example.study_quiz_ai.modules.ai;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.study_quiz_ai.modules.ai.dto.PromptRequestDto;
import com.example.study_quiz_ai.modules.ai.dto.PromptResponseDto;
import com.example.study_quiz_ai.modules.ai.service.impl.GeminiServiceImpl;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/gemini")
@Tag(name = "Gemini API")
@RequiredArgsConstructor
public class GeminiController {
    private final GeminiServiceImpl geminiService;

    @PostMapping("/generate")
    public ResponseEntity<?> generate(@RequestBody PromptRequestDto dto) {
        try {
            String result = geminiService.generate(dto);
            return ResponseEntity.ok(new PromptResponseDto(result));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}
