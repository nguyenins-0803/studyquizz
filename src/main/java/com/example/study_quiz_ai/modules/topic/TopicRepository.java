package com.example.study_quiz_ai.modules.topic;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.study_quiz_ai.modules.topic.entity.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long>, JpaSpecificationExecutor<Topic> {
    Optional<Topic> findByNameIgnoreCase(String name);
}
