package com.example.study_quiz_ai.modules.topic.service;

import com.example.study_quiz_ai.core.base.ApiResponse;
import com.example.study_quiz_ai.core.base.PagedAndResult;
import com.example.study_quiz_ai.modules.topic.dto.CreateOrEditTopicDto;
import com.example.study_quiz_ai.modules.topic.dto.GetTopicDto;
import com.example.study_quiz_ai.modules.topic.dto.GetTopicForFilterDto;

public interface TopicService {

    ApiResponse<CreateOrEditTopicDto> create(CreateOrEditTopicDto topicDto);

    PagedAndResult<GetTopicDto> getAll(GetTopicForFilterDto request);

    GetTopicDto getById(Long id);

    ApiResponse<CreateOrEditTopicDto> update(Long id, CreateOrEditTopicDto topicDto);

    void delete(Long id);
}
