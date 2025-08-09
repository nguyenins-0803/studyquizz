package com.example.study_quiz_ai.core.base;

import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApiError {
    private HttpStatus status;
    private String message;
    private List<String> errors;
    private String timestamp;
}
