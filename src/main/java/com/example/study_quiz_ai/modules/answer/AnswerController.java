package com.example.study_quiz_ai.modules.answer;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.study_quiz_ai.modules.answer.dto.CreateOrEditAnswerDto;
import com.example.study_quiz_ai.modules.answer.entity.Answer;
import com.example.study_quiz_ai.modules.answer.service.AnswerService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/answers")
@Tag(name = "Answers")
@RequiredArgsConstructor
public class AnswerController {
    private final AnswerService answerService;

    @PostMapping()
    public Answer create(@RequestBody CreateOrEditAnswerDto answerDto) {
        return answerService.create(answerDto);
    }

    @PutMapping("/{id}")
    public Answer update(@PathVariable Long id, @RequestBody CreateOrEditAnswerDto answerDto) {
        return answerService.update(id, answerDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        answerService.delete(id);
    }
}
