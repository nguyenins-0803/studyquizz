package com.example.study_quiz_ai.modules.history.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistoryResponseDto {
    private Long id;
    private String topic;
    private String date;           // ISO string để FE format
    private Integer correctAnswers;
    private Integer totalQuestions;
    private Double accuracy;
    private String status;
    private String duration;
}
