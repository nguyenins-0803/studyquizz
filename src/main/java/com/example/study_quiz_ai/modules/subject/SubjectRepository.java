package com.example.study_quiz_ai.modules.subject;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.study_quiz_ai.modules.subject.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
