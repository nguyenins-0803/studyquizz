package com.example.study_quiz_ai.modules.question.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.study_quiz_ai.modules.answer.entity.Answer;
import com.example.study_quiz_ai.modules.question.enums.QuestionDifficulty;
import com.example.study_quiz_ai.modules.question.enums.QuestionType;
import com.example.study_quiz_ai.modules.quiz.entity.Quiz;
import com.example.study_quiz_ai.modules.topic.entity.Topic;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "questions")
@Getter
@Setter
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @Enumerated(EnumType.STRING)
    private QuestionType type;

    @Enumerated(EnumType.STRING)
    private QuestionDifficulty difficulty;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Answer> answers;

    @ManyToMany(mappedBy = "questions")
    private Set<Quiz> quizzes = new HashSet<>();

    @ManyToMany()
    @JoinTable(name = "question_topics", joinColumns = @JoinColumn(name = "question_id"), inverseJoinColumns = @JoinColumn(name = "topic_id"))
    private Set<Topic> topics = new HashSet<>();
}
