package com.example.study_quiz_ai.modules.topic.service;

import java.util.List;

import com.example.study_quiz_ai.core.base.PagedAndResult;
import com.example.study_quiz_ai.modules.topic.dto.CreateOrEditTopicDto;
import com.example.study_quiz_ai.modules.topic.dto.GetTopicDto;
import com.example.study_quiz_ai.modules.topic.dto.GetTopicForFilterDto;
import com.example.study_quiz_ai.modules.topic.entity.Topic;
import com.example.study_quiz_ai.modules.user.entity.User;

public interface TopicService {
    Topic searchOrCreateTopic(String topicName);

    List<Object[]> getHotTopics();

    void logSearch(Topic topic, String topicName, User user);

    CreateOrEditTopicDto create(CreateOrEditTopicDto topicDto);

    PagedAndResult<GetTopicDto> getAll(GetTopicForFilterDto request);

    GetTopicDto getById(Long id);

    CreateOrEditTopicDto update(Long id, CreateOrEditTopicDto topicDto);

    void delete(Long id);
}
