package com.example.study_quiz_ai.core.base;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponseError {
    private int status;
    private String error;
    private String message;
    private String path;
    private LocalDateTime timestamp;
}
