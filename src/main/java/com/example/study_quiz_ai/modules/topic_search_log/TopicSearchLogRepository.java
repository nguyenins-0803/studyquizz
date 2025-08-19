package com.example.study_quiz_ai.modules.topic_search_log;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.study_quiz_ai.modules.topic_search_log.entity.TopicSearchLog;

public interface TopicSearchLogRepository extends JpaRepository<TopicSearchLog, Long> {
    @Query("SELECT t.topicName, COUNT(t) FROM TopicSearchLog t "
            + "GROUP BY t.topicName ORDER BY COUNT(t) DESC")
    List<Object[]> findHotTopics();
}
