package com.example.study_quiz_ai.modules.quiz.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.study_quiz_ai.core.base.PagedAndResult;
import com.example.study_quiz_ai.core.exception.NotFoundException;
import com.example.study_quiz_ai.modules.quiz.QuizRepository;
import com.example.study_quiz_ai.modules.quiz.dto.GetQuizDto;
import com.example.study_quiz_ai.modules.quiz.dto.GetQuizForFilterDto;
import com.example.study_quiz_ai.modules.quiz.dto.GetQuizForViewDto;
import com.example.study_quiz_ai.modules.quiz.entity.Quiz;
import com.example.study_quiz_ai.modules.quiz.service.QuizService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {
    private final QuizRepository quizRepository;
    private final ModelMapper modelMapper;

    @Override
    public PagedAndResult<GetQuizForViewDto> getAll(GetQuizForFilterDto request) {
        Specification<Quiz> spec = (root, query, criteriaBuilder) -> {
            if (request.getFilterText() != null) {
                criteriaBuilder.like(root.get("title"), "%" + request.getFilterText() + "%");
            }
            return criteriaBuilder.conjunction();
        };

        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());

        Page<Quiz> page = quizRepository.findAll(spec, pageable);

        List<GetQuizForViewDto> quizDtos = page.getContent().stream()
                .map(quiz -> modelMapper.map(quiz, GetQuizForViewDto.class))
                .toList();

        return new PagedAndResult<>(quizDtos, page.getNumber(), page.getSize(), page.getTotalElements(),
                page.getTotalPages());
    }

    @Override
    public GetQuizDto getById(Long id) {
        Quiz quiz = quizRepository.findById(id).orElseThrow(() -> new NotFoundException("Quiz not found"));
        return modelMapper.map(quiz, GetQuizDto.class);
    }

    @Override
    public void delete(Long id) {
        Quiz quiz = quizRepository.findById(id).orElseThrow(() -> new NotFoundException("Quiz not found"));
        quizRepository.delete(quiz);
    }
}
