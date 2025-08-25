package com.example.study_quiz_ai.modules.history.service;

import java.util.List;

import com.example.study_quiz_ai.modules.history.dto.HistoryRequestDto;
import com.example.study_quiz_ai.modules.history.dto.HistoryResponseDto;

public interface HistoryService {
    List<HistoryResponseDto> getHistoryByUser(Long userId);
    HistoryResponseDto saveHistory(HistoryRequestDto request);

}
