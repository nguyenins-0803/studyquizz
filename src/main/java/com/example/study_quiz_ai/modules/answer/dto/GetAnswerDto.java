package com.example.study_quiz_ai.modules.answer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema
public class GetAnswerDto {
    private Long id;
    private String content;
    private boolean isCorrect;
}
