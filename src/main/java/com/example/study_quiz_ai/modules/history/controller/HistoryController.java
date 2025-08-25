package com.example.study_quiz_ai.modules.history.controller;

import com.example.study_quiz_ai.core.base.ApiResponse;
import com.example.study_quiz_ai.modules.history.dto.HistoryRequestDto;
import com.example.study_quiz_ai.modules.history.dto.HistoryResponseDto;
import com.example.study_quiz_ai.modules.history.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/history")
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    // GET /api/history/user/{userId}
    @GetMapping("/user/{userId}")
    public ApiResponse<List<HistoryResponseDto>> getHistoryByUser(@PathVariable Long userId) {
        return ApiResponse.success(historyService.getHistoryByUser(userId));
    }

    // POST /api/history
    @PostMapping
    public ApiResponse<HistoryResponseDto> saveHistory(@RequestBody HistoryRequestDto request) {
        return ApiResponse.success(historyService.saveHistory(request));
    }

}
