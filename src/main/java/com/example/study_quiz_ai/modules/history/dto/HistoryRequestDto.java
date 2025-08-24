package com.example.study_quiz_ai.modules.history.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistoryRequestDto {
    private Long userId;
    private Long quizId;
    private String topic;
    private Integer correctAnswers;
    private Integer totalQuestions;
    private String duration;
}
