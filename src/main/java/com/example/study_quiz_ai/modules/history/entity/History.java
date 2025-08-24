package com.example.study_quiz_ai.modules.history.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "histories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;          // Ai làm quiz
    private Long quizId;          // ID quiz
    private String topic;         // Chủ đề quiz (cache để FE không join nhiều)

    private LocalDateTime date;   // Ngày làm quiz
    private Integer correctAnswers;
    private Integer totalQuestions;
    private Double accuracy;      // Tỉ lệ % (VD: 90.0)
    private String status;        // excellent / good / needs_improvement
    private String duration;      // Thời gian làm (8 phút, 12 phút,…)
}
