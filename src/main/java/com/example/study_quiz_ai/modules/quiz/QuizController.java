package com.example.study_quiz_ai.modules.quiz;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.study_quiz_ai.modules.quiz.dto.GetQuizDto;
import com.example.study_quiz_ai.modules.quiz.service.QuizService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/quizzes")
@Tag(name = "Quiz")
@RequiredArgsConstructor
public class QuizController {
    private final QuizService quizService;

    @GetMapping("/{id}")
    public GetQuizDto getQuizById(@PathVariable Long id) {
        return quizService.getById(id);
    }

}
