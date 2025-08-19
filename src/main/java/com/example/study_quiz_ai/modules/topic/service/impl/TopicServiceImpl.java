package com.example.study_quiz_ai.modules.topic.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.study_quiz_ai.core.base.PagedAndResult;
import com.example.study_quiz_ai.core.exception.NotFoundException;
import com.example.study_quiz_ai.modules.topic.TopicRepository;
import com.example.study_quiz_ai.modules.topic.dto.CreateOrEditTopicDto;
import com.example.study_quiz_ai.modules.topic.dto.GetTopicDto;
import com.example.study_quiz_ai.modules.topic.dto.GetTopicForFilterDto;
import com.example.study_quiz_ai.modules.topic.entity.Topic;
import com.example.study_quiz_ai.modules.topic.service.TopicService;
import com.example.study_quiz_ai.modules.topic_search_log.TopicSearchLogRepository;
import com.example.study_quiz_ai.modules.topic_search_log.entity.TopicSearchLog;
import com.example.study_quiz_ai.modules.user.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService {
    private final TopicRepository topicRepository;
    private final TopicSearchLogRepository topicSearchLogRepository;
    private final ModelMapper modelMapper;

    @Override
    public Topic searchOrCreateTopic(String topicName) {
        return topicRepository.findByNameIgnoreCase(topicName)
                .orElseGet(() -> {
                    Topic t = Topic.builder()
                            .name(topicName)
                            .build();
                    return topicRepository.save(t);
                });
    }

    @Override
    public void logSearch(Topic topic, String topicName, User user) {
        TopicSearchLog log = TopicSearchLog.builder()
                .topic(topic)
                .topicName(topicName)
                .user(user)
                .build();
        topicSearchLogRepository.save(log);
    }

    @Override
    public List<Object[]> getHotTopics() {
        return topicSearchLogRepository.findHotTopics();
    }

    @Override
    public CreateOrEditTopicDto create(CreateOrEditTopicDto createTopicDto) {
        Topic topic = modelMapper.map(createTopicDto, Topic.class);
        topic = topicRepository.save(topic);
        return modelMapper.map(topic, CreateOrEditTopicDto.class);
    }

    @Override
    public PagedAndResult<GetTopicDto> getAll(GetTopicForFilterDto request) {
        Specification<Topic> spec = (root, query, criteriaBuilder) -> {
            if (request.getFilterText() != null && !request.getFilterText().isEmpty()) {
                return criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("name")),
                        "%" + request.getFilterText().toLowerCase() + "%");
            }
            return criteriaBuilder.conjunction();
        };

        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());

        Page<Topic> page = topicRepository.findAll(spec, pageable);

        List<GetTopicDto> content = page.getContent()
                .stream()
                .map(topic -> modelMapper.map(topic, GetTopicDto.class))
                .toList();

        return new PagedAndResult<>(content, page.getNumber(), page.getSize(), page.getTotalElements(),
                page.getTotalPages());
    }

    @Override
    public GetTopicDto getById(Long id) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Topic not found"));
        return modelMapper.map(topic, GetTopicDto.class);
    }

    @Override
    public CreateOrEditTopicDto update(Long id, CreateOrEditTopicDto createOrEditTopicDto) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Topic not found"));
        modelMapper.map(createOrEditTopicDto, topic);
        topic = topicRepository.save(topic);
        return modelMapper.map(topic, CreateOrEditTopicDto.class);
    }

    @Override
    public void delete(Long id) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Topic not found"));
        topicRepository.delete(topic);
    }
}
