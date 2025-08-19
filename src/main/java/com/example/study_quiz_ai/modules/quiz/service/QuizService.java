package com.example.study_quiz_ai.modules.quiz.service;

import com.example.study_quiz_ai.core.base.PagedAndResult;
import com.example.study_quiz_ai.modules.quiz.dto.GetQuizDto;
import com.example.study_quiz_ai.modules.quiz.dto.GetQuizForFilterDto;
import com.example.study_quiz_ai.modules.quiz.dto.GetQuizForViewDto;

public interface QuizService {
    PagedAndResult<GetQuizForViewDto> getAll(GetQuizForFilterDto request);

    GetQuizDto getById(Long id);

    void delete(Long id);
}