package com.example.study_quiz_ai.modules.grade.entity;

import java.util.HashSet;
import java.util.Set;

import com.example.study_quiz_ai.modules.subject.entity.Subject;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "grades")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    // Quan hệ n-n với Subject
    @ManyToMany(mappedBy = "grades")
    private Set<Subject> subjects = new HashSet<>();

}
