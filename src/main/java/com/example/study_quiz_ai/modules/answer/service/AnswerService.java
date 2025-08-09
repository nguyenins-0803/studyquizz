package com.example.study_quiz_ai.modules.answer.service;

import com.example.study_quiz_ai.modules.answer.dto.CreateOrEditAnswerDto;
import com.example.study_quiz_ai.modules.answer.entity.Answer;

public interface AnswerService {
    Answer create(CreateOrEditAnswerDto answerDto);

    Answer update(Long id, CreateOrEditAnswerDto answerDto);

    void delete(Long id);
}
