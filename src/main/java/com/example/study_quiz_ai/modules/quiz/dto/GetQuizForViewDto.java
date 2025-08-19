package com.example.study_quiz_ai.modules.quiz.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema
@Getter
@Setter
public class GetQuizForViewDto {
    private Long id;
    private String title;
    private String description;
    private String difficulty;
}
