package com.example.study_quiz_ai.modules.question.dto;

import java.util.List;

import com.example.study_quiz_ai.modules.answer.dto.GetAnswerDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema
@Getter
@Setter
public class GetQuestionDto {
    private Long id;
    private String content;
    private boolean isMultipleChoice;
    private List<GetAnswerDto> answers;
}
