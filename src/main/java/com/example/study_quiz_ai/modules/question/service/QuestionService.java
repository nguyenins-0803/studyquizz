package com.example.study_quiz_ai.modules.question.service;

import com.example.study_quiz_ai.core.base.PagedAndResult;
import com.example.study_quiz_ai.modules.question.dto.CreateOrEditQuestionDto;
import com.example.study_quiz_ai.modules.question.dto.GetQuestionDto;
import com.example.study_quiz_ai.modules.question.dto.GetQuestionsForFilterDto;
import com.example.study_quiz_ai.modules.question.entity.Question;

public interface QuestionService {
    Question create(CreateOrEditQuestionDto questionDto);

    PagedAndResult<GetQuestionDto> getAll(GetQuestionsForFilterDto request);

    GetQuestionDto getById(Long id);

    Question update(Long id, CreateOrEditQuestionDto questionDto);

    void delete(Long id);
}