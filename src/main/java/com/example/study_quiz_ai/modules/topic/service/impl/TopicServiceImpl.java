package com.example.study_quiz_ai.modules.topic.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.study_quiz_ai.core.base.ApiResponse;
import com.example.study_quiz_ai.core.base.PagedAndResult;
import com.example.study_quiz_ai.core.exception.NotFoundException;
import com.example.study_quiz_ai.modules.subject.SubjectRepository;
import com.example.study_quiz_ai.modules.subject.entity.Subject;
import com.example.study_quiz_ai.modules.topic.TopicRepository;
import com.example.study_quiz_ai.modules.topic.dto.CreateOrEditTopicDto;
import com.example.study_quiz_ai.modules.topic.dto.GetTopicDto;
import com.example.study_quiz_ai.modules.topic.dto.GetTopicForFilterDto;
import com.example.study_quiz_ai.modules.topic.entity.Topic;
import com.example.study_quiz_ai.modules.topic.service.TopicService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService {
    private final TopicRepository topicRepository;
    private final SubjectRepository subjectRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public ApiResponse<CreateOrEditTopicDto> create(CreateOrEditTopicDto dto) {
        Topic topic = new Topic();
        topic.setName(dto.getName());
        topic.setDescription(dto.getDescription());

        Set<Subject> subjects = new HashSet<>(subjectRepository.findAllByIdWithoutGrades(dto.getSubjectIds()));
        if (subjects.isEmpty()) {
            return ApiResponse.error("Subjects not found");
        }
        topic.setSubjects(subjects);

        topicRepository.save(topic);

        CreateOrEditTopicDto resultDto = new CreateOrEditTopicDto();
        resultDto.setName(topic.getName());
        resultDto.setDescription(topic.getDescription());
        resultDto.setSubjectIds(topic.getSubjects().stream().map(Subject::getId).collect(Collectors.toSet()));

        return ApiResponse.success(resultDto, "Topic created successfully");
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
    public ApiResponse<CreateOrEditTopicDto> update(Long id, CreateOrEditTopicDto dto) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Topic not found"));

        topic.setName(dto.getName());
        topic.setDescription(dto.getDescription());

        Set<Subject> subjects = new HashSet<>(subjectRepository.findAllById(dto.getSubjectIds()));
        if (subjects.isEmpty()) {
            return ApiResponse.error("Subjects not found");
        }
        topic.setSubjects(subjects);

        topic = topicRepository.save(topic);

        CreateOrEditTopicDto resultDto = new CreateOrEditTopicDto();
        resultDto.setName(topic.getName());
        resultDto.setDescription(topic.getDescription());
        resultDto.setSubjectIds(topic.getSubjects().stream().map(Subject::getId).collect(Collectors.toSet()));

        return ApiResponse.success(resultDto, "Topic updated successfully");
    }

    @Override
    public void delete(Long id) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Topic not found"));
        topicRepository.delete(topic);
    }
}
