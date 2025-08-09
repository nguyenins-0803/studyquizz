package com.example.study_quiz_ai.modules.answer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema
public class CreateOrEditAnswerDto {
    @NotBlank
    private String content;

    @NotNull
    private Boolean isCorrect;
}
