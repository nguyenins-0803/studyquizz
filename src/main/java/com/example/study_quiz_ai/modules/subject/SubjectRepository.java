package com.example.study_quiz_ai.modules.subject;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.study_quiz_ai.modules.subject.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    @Query("SELECT s FROM Subject s LEFT JOIN FETCH s.grades WHERE s.id IN :ids")
    List<Subject> findAllByIdWithoutGrades(@Param("ids") Collection<Long> ids);
}
