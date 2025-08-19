package com.example.study_quiz_ai.modules.topic.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.example.study_quiz_ai.modules.question.entity.Question;
import com.example.study_quiz_ai.modules.quiz.entity.Quiz;
import com.example.study_quiz_ai.modules.topic_search_log.entity.TopicSearchLog;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "topics")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Builder.Default
    @ManyToMany()
    @JoinTable(name = "topic_quiz", joinColumns = @JoinColumn(name = "topic_id"), inverseJoinColumns = @JoinColumn(name = "quiz_id"))
    private Set<Quiz> quizzes = new HashSet<>();

    @Builder.Default
    @ManyToMany(mappedBy = "topics")
    private Set<Question> questions = new HashSet<>();

    @Builder.Default
    @OneToMany(mappedBy = "topic")
    private Set<TopicSearchLog> searchLogs = new HashSet<>();

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
