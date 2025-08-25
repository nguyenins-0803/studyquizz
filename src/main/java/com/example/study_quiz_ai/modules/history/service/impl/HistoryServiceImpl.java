package com.example.study_quiz_ai.modules.history.service.impl;

import com.example.study_quiz_ai.modules.history.dto.HistoryRequestDto;
import com.example.study_quiz_ai.modules.history.dto.HistoryResponseDto;
import com.example.study_quiz_ai.modules.history.entity.History;
import com.example.study_quiz_ai.modules.history.repository.HistoryRepository;
import com.example.study_quiz_ai.modules.history.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private HistoryRepository historyRepository;

    @Override
    public List<HistoryResponseDto> getHistoryByUser(Long userId) {
        List<History> histories = historyRepository.findByUserId(userId);
        return histories.stream().map(h -> HistoryResponseDto.builder()
                .id(h.getId())
                .topic(h.getTopic())
                .date(h.getDate().toString()) // FE sẽ format lại
                .correctAnswers(h.getCorrectAnswers())
                .totalQuestions(h.getTotalQuestions())
                .accuracy(h.getAccuracy())
                .status(h.getStatus())
                .duration(h.getDuration())
                .build()).collect(Collectors.toList());
    }

    @Override
    public HistoryResponseDto saveHistory(HistoryRequestDto request) {
        // Tính toán accuracy
        double accuracy = 0.0;
        if (request.getTotalQuestions() != null && request.getTotalQuestions() > 0) {
            accuracy = (request.getCorrectAnswers() * 100.0) / request.getTotalQuestions();
        }

        // Đánh giá status
        String status;
        if (accuracy >= 80) {
            status = "excellent";
        } else if (accuracy >= 50) {
            status = "good";
        } else {
            status = "needs_improvement";
        }

        // Tạo entity để lưu DB
        History history = History.builder()
                .userId(request.getUserId())
                .quizId(request.getQuizId())
                .topic(request.getTopic())
                .date(LocalDateTime.now())
                .correctAnswers(request.getCorrectAnswers())
                .totalQuestions(request.getTotalQuestions())
                .accuracy(accuracy)
                .status(status)
                .duration(request.getDuration())
                .build();

        history = historyRepository.save(history);

        // Trả DTO về cho FE
        return HistoryResponseDto.builder()
                .id(history.getId())
                .topic(history.getTopic())
                .date(history.getDate().toString())
                .correctAnswers(history.getCorrectAnswers())
                .totalQuestions(history.getTotalQuestions())
                .accuracy(history.getAccuracy())
                .status(history.getStatus())
                .duration(history.getDuration())
                .build();
    }

}
