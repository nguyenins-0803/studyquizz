package com.example.study_quiz_ai.modules.ai;

import org.springframework.web.bind.annotation.RestController;

import com.example.study_quiz_ai.modules.ai.service.GeminiService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/gemini")
@Tag(name = "Gemini API")
@RequiredArgsConstructor
public class GeminiController {
    private final GeminiService geminiService;

    @GetMapping()
    public String testGemini(@RequestParam String param) {
        return geminiService.testGemini(param);
    }

}
