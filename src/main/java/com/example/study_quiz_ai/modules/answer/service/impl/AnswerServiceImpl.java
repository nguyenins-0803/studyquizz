package com.example.study_quiz_ai.modules.answer.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.study_quiz_ai.modules.answer.AnswerRepository;
import com.example.study_quiz_ai.modules.answer.dto.CreateOrEditAnswerDto;
import com.example.study_quiz_ai.modules.answer.entity.Answer;
import com.example.study_quiz_ai.modules.answer.service.AnswerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {
    private final AnswerRepository answerRepository;
    private final ModelMapper modelMapper;

    @Override
    public Answer create(CreateOrEditAnswerDto answerDto) {
        Answer answer = modelMapper.map(answerDto, Answer.class);
        return answerRepository.save(answer);
    }

    @Override
    public Answer update(Long id, CreateOrEditAnswerDto answerDto) {
        Answer answer = answerRepository.findById(id).orElse(null);
        if (answer != null) {
            modelMapper.map(answerDto, answer);
            return answerRepository.save(answer);
        }
        return answer;
    }

    @Override
    public void delete(Long id) {
        answerRepository.deleteById(id);
    }

}
