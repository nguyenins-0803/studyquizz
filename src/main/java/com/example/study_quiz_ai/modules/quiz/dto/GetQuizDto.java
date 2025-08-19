package com.example.study_quiz_ai.modules.quiz.dto;

import java.util.List;

import com.example.study_quiz_ai.modules.question.dto.GetQuestionDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema
@Getter
@Setter
public class GetQuizDto {
    private Long id;
    private List<GetQuestionDto> questions;
}
