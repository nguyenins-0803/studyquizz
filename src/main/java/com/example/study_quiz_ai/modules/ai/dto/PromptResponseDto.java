package com.example.study_quiz_ai.modules.ai.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Schema
@Getter
@Setter
@AllArgsConstructor
public class PromptResponseDto {
    private String output;
}
