package com.example.study_quiz_ai.modules.subject.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.study_quiz_ai.core.base.ApiResponse;
import com.example.study_quiz_ai.modules.subject.SubjectRepository;
import com.example.study_quiz_ai.modules.subject.dto.CreateOrEditSubjectDto;
import com.example.study_quiz_ai.modules.subject.dto.GetSubjectDto;
import com.example.study_quiz_ai.modules.subject.entity.Subject;
import com.example.study_quiz_ai.modules.subject.service.SubjectService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;
    private final ModelMapper modelMapper;

    @Override
    public ApiResponse<List<GetSubjectDto>> getAll() {
        List<Subject> subjects = subjectRepository.findAll();
        List<GetSubjectDto> subjectDtos = subjects.stream()
                .map(subject -> modelMapper.map(subject, GetSubjectDto.class))
                .collect(Collectors.toList());
        return ApiResponse.success(subjectDtos);
    }

    @Override
    public ApiResponse<CreateOrEditSubjectDto> create(CreateOrEditSubjectDto dto) {
        Subject subject = modelMapper.map(dto, Subject.class);
        subject = subjectRepository.save(subject);
        return ApiResponse.success(modelMapper.map(subject, CreateOrEditSubjectDto.class),
                "Subject created successfully");
    }

    @Override
    public ApiResponse<CreateOrEditSubjectDto> update(Long id, CreateOrEditSubjectDto dto) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Subject not found"));
        modelMapper.map(dto, subject);
        subject = subjectRepository.save(subject);
        return ApiResponse.success(modelMapper.map(subject, CreateOrEditSubjectDto.class),
                "Subject updated successfully");
    }

    @Override
    public ApiResponse<Void> delete(Long id) {
        subjectRepository.deleteById(id);
        return ApiResponse.success(
                null,
                "Subject deleted successfully");
    }
}
