package com.example.study_quiz_ai.modules.grade;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.study_quiz_ai.modules.grade.entity.Grade;

public interface GradeRepository extends JpaRepository<Grade, Long> {

}
