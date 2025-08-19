package com.example.study_quiz_ai.modules.question.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.study_quiz_ai.core.base.PagedAndResult;
import com.example.study_quiz_ai.core.exception.NotFoundException;
import com.example.study_quiz_ai.modules.answer.entity.Answer;
import com.example.study_quiz_ai.modules.question.QuestionRepository;
import com.example.study_quiz_ai.modules.question.dto.CreateOrEditQuestionDto;
import com.example.study_quiz_ai.modules.question.dto.GetQuestionDto;
import com.example.study_quiz_ai.modules.question.dto.GetQuestionsForFilterDto;
import com.example.study_quiz_ai.modules.question.entity.Question;
import com.example.study_quiz_ai.modules.question.service.QuestionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final ModelMapper modelMapper;

    @Override
    public Question create(CreateOrEditQuestionDto createQuestionDto) {
        Question question = modelMapper.map(createQuestionDto, Question.class);

        if (createQuestionDto.getAnswers() != null && !createQuestionDto.getAnswers().isEmpty()) {
            List<Answer> answers = createQuestionDto.getAnswers().stream()
                    .map(answerDto -> {
                        Answer answer = modelMapper.map(answerDto, Answer.class);
                        answer.setQuestion(question);
                        return answer;
                    }).collect(Collectors.toList());

            question.setAnswers(answers);
        }

        return questionRepository.save(question);
    }

    @Override
    public PagedAndResult<GetQuestionDto> getAll(GetQuestionsForFilterDto request) {
        Specification<Question> spec = (root, query, criteriaBuilder) -> {
            if (StringUtils.hasText(request.getFilterText())) {
                return criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("content")),
                        "%" + request.getFilterText().toLowerCase() + "%");
            }
            return criteriaBuilder.conjunction();
        };
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());

        Page<Question> page = questionRepository.findAll(spec, pageable);

        List<GetQuestionDto> content = page.getContent()
                .stream()
                .map(question -> modelMapper.map(question, GetQuestionDto.class))
                .toList();

        return new PagedAndResult<>(content, page.getNumber(), page.getSize(),
                page.getTotalElements(), page.getTotalPages());
    }

    @Override
    public GetQuestionDto getById(Long id) {
        Question question = questionRepository.findById(id).orElse(null);
        return modelMapper.map(question, GetQuestionDto.class);
    }

    @Override
    public Question update(Long id, CreateOrEditQuestionDto questionDto) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Question not found"));
        modelMapper.map(questionDto, question);
        return questionRepository.save(question);
    }

    @Override
    public void delete(Long id) {
        questionRepository.deleteById(id);
    }
}
