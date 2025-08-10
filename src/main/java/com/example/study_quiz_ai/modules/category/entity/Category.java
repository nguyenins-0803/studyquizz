package com.example.study_quiz_ai.modules.category.entity;

import java.util.HashSet;
import java.util.Set;

import com.example.study_quiz_ai.modules.quiz.entity.Quiz;

import jakarta.persistence.Entity;
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
@Table(name = "categories")
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany()
    @JoinTable(name = "category_quiz", joinColumns = @JoinColumn(name = "category_id"), inverseJoinColumns = @JoinColumn(name = "quiz_id"))
    private Set<Quiz> quizzes = new HashSet<>();
}
