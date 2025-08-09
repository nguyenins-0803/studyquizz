package com.example.study_quiz_ai.modules.answer;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.study_quiz_ai.modules.answer.entity.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
