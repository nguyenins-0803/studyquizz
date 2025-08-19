package com.example.study_quiz_ai.modules.question.dto;

import java.util.List;

import com.example.study_quiz_ai.modules.answer.dto.CreateOrEditAnswerDto;
import com.example.study_quiz_ai.modules.question.enums.QuestionDifficulty;
import com.example.study_quiz_ai.modules.question.enums.QuestionType;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema
public class CreateOrEditQuestionDto {
    @NotBlank
    private String content;

    @NotNull
    private QuestionType type;

    @NotNull
    private QuestionDifficulty difficulty;

    @NotNull
    private List<CreateOrEditAnswerDto> answers;
}
