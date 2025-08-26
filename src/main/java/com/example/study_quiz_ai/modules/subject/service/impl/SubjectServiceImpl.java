package com.example.study_quiz_ai.modules.subject.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.study_quiz_ai.core.base.ApiResponse;
import com.example.study_quiz_ai.core.exception.NotFoundException;
import com.example.study_quiz_ai.modules.grade.GradeRepository;
import com.example.study_quiz_ai.modules.grade.entity.Grade;
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
    private final GradeRepository gradeRepository;
    private final ModelMapper modelMapper;

    @Override
    public ApiResponse<CreateOrEditSubjectDto> create(CreateOrEditSubjectDto dto) {
        Subject subject = new Subject();
        subject.setName(dto.getName());
        subject.setCode(dto.getCode());
        subject.setDescription(dto.getDescription());

        Set<Grade> grades = new HashSet<>(gradeRepository.findAllById(dto.getGradeIds()));
        if (grades.isEmpty()) {
            return ApiResponse.error("Grades not found");
        }
        subject.setGrades(grades);

        subjectRepository.save(subject);

        CreateOrEditSubjectDto resultDto = new CreateOrEditSubjectDto();
        resultDto.setName(subject.getName());
        resultDto.setCode(subject.getCode());
        resultDto.setDescription(subject.getDescription());
        resultDto.setGradeIds(subject.getGrades().stream().map(Grade::getId).collect(Collectors.toSet()));

        return ApiResponse.success(resultDto, "Subject created successfully");
    }

    @Override
    public ApiResponse<List<GetSubjectDto>> getAll() {
        List<Subject> subjects = subjectRepository.findAll();
        List<GetSubjectDto> subjectDtos = subjects.stream()
                .map(subject -> modelMapper.map(subject, GetSubjectDto.class))
                .collect(Collectors.toList());
        return ApiResponse.success(subjectDtos);
    }

    @Override
    public ApiResponse<GetSubjectDto> getById(Long id) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Subject not found"));
        return ApiResponse.success(modelMapper.map(subject, GetSubjectDto.class));
    }

    @Override
    public ApiResponse<CreateOrEditSubjectDto> update(Long id, CreateOrEditSubjectDto dto) {
        // Find the existing subject
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Subject not found"));

        // Update subject details
        subject.setName(dto.getName());
        subject.setCode(dto.getCode());
        subject.setDescription(dto.getDescription());

        // Validate and set grades
        Set<Grade> grades = new HashSet<>(gradeRepository.findAllById(dto.getGradeIds()));
        if (grades.isEmpty()) {
            return ApiResponse.error("Grades not found");
        }
        subject.setGrades(grades);

        subjectRepository.save(subject);

        CreateOrEditSubjectDto resultDto = new CreateOrEditSubjectDto();
        resultDto.setName(subject.getName());
        resultDto.setCode(subject.getCode());
        resultDto.setDescription(subject.getDescription());
        resultDto.setGradeIds(subject.getGrades().stream().map(Grade::getId).collect(Collectors.toSet()));

        return ApiResponse.success(resultDto, "Subject updated successfully");
    }

    @Override
    public ApiResponse<Void> delete(Long id) {
        subjectRepository.deleteById(id);
        return ApiResponse.success(
                null,
                "Subject deleted successfully");
    }
}
