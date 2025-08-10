package com.example.study_quiz_ai.modules.quiz_session.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.study_quiz_ai.modules.quiz.entity.Quiz;
import com.example.study_quiz_ai.modules.user.entity.User;
import com.example.study_quiz_ai.modules.user_answers.entity.UserAnswer;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "quiz_sessions")
@Getter
@Setter
public class QuizSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Float score;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @OneToMany(mappedBy = "quizSession", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserAnswer> questions = new ArrayList<>();

    private LocalDateTime startTime;

    private LocalDateTime endTime;
}
