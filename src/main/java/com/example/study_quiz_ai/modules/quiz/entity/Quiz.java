package com.example.study_quiz_ai.modules.quiz.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import com.example.study_quiz_ai.modules.question.entity.Question;
import com.example.study_quiz_ai.modules.quiz.enums.QuizDifficulty;
import com.example.study_quiz_ai.modules.topic.entity.Topic;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "quizzes")
@Getter
@Setter
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false, length = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('EASY', 'MEDIUM', 'HARD')")
    private QuizDifficulty difficulty;

    // Metadata for AI
    @Column(name = "avg_completion_time")
    private Long avgCompletionTime;

    @Column(name = "avg_score", precision = 3, scale = 2)
    private BigDecimal avgScore;

    @Column(name = "is_ai_generated")
    private Boolean isAiGenerated = false;

    @Column(name = "ai_metadata", columnDefinition = "JSON")
    private String aiMetadata;

    @Column(name = "total_attempts")
    private Integer totalAttempts = 0;

    @Column(name = "success_rate", precision = 3, scale = 2)
    private BigDecimal successRate;

    @ManyToMany(mappedBy = "quizzes")
    private Set<Topic> topics = new HashSet<>();

    // Quan hệ với Question (n-n)
    @ManyToMany
    @JoinTable(name = "quiz_questions", joinColumns = @JoinColumn(name = "quiz_id"), inverseJoinColumns = @JoinColumn(name = "question_id"))
    private Set<Question> questions = new HashSet<>();
}
