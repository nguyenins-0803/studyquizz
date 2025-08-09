package com.example.study_quiz_ai.modules.question;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.study_quiz_ai.core.base.PagedAndResult;
import com.example.study_quiz_ai.modules.question.dto.CreateOrEditQuestionDto;
import com.example.study_quiz_ai.modules.question.dto.GetQuestionDto;
import com.example.study_quiz_ai.modules.question.dto.GetQuestionsForFilterDto;
import com.example.study_quiz_ai.modules.question.entity.Question;
import com.example.study_quiz_ai.modules.question.service.QuestionService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/questions")
@Tag(name = "Questions")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @PostMapping
    public ResponseEntity<Question> create(@RequestBody @Valid CreateOrEditQuestionDto questionDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(questionService.create(questionDto));
    }

    @GetMapping()
    public ResponseEntity<PagedAndResult<GetQuestionDto>> getAll(
            @ModelAttribute @Valid GetQuestionsForFilterDto getQuestionsForFilterDto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(questionService.getAll(getQuestionsForFilterDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetQuestionDto> getById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(questionService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Question> update(@PathVariable Long id,
            @RequestBody @Valid CreateOrEditQuestionDto questionDto) {
        Question updatedQuestion = questionService.update(id, questionDto);
        if (updatedQuestion != null) {
            return ResponseEntity.status(HttpStatus.OK).body(updatedQuestion);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        questionService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
