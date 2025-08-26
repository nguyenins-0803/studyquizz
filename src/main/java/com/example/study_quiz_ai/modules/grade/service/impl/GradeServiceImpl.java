package com.example.study_quiz_ai.modules.grade.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.study_quiz_ai.core.base.ApiResponse;
import com.example.study_quiz_ai.modules.grade.GradeRepository;
import com.example.study_quiz_ai.modules.grade.dto.CreateOrEditGradeDto;
import com.example.study_quiz_ai.modules.grade.dto.GetGradeForViewDto;
import com.example.study_quiz_ai.modules.grade.entity.Grade;
import com.example.study_quiz_ai.modules.grade.service.GradeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GradeServiceImpl implements GradeService {
    private final GradeRepository gradeRepository;
    private final ModelMapper modelMapper;

    @Override
    public ApiResponse<CreateOrEditGradeDto> create(CreateOrEditGradeDto dto) {
        Grade grade = modelMapper.map(dto, Grade.class);
        grade = gradeRepository.save(grade);
        return ApiResponse.success(modelMapper.map(grade, CreateOrEditGradeDto.class),
                "Grade created successfully");
    }

    @Override
    public ApiResponse<List<GetGradeForViewDto>> getAll() {
        List<Grade> grades = gradeRepository.findAll();
        List<GetGradeForViewDto> gradeDtos = grades.stream()
                .map(grade -> modelMapper.map(grade, GetGradeForViewDto.class))
                .collect(Collectors.toList());
        return ApiResponse.success(gradeDtos);
    }

    @Override
    public ApiResponse<GetGradeForViewDto> getById(Long id) {
        GetGradeForViewDto grade = gradeRepository.findById(id)
                .map(entity -> modelMapper.map(entity, GetGradeForViewDto.class))
                .orElseThrow(() -> new IllegalArgumentException("Grade not found"));
        return ApiResponse.success(grade);
    }

    @Override
    public ApiResponse<CreateOrEditGradeDto> update(Long id, CreateOrEditGradeDto dto) {
        Grade grade = gradeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Grade not found"));
        modelMapper.map(dto, grade);
        grade = gradeRepository.save(grade);
        return ApiResponse.success(modelMapper.map(grade, CreateOrEditGradeDto.class),
                "Grade updated successfully");
    }

    @Override
    public ApiResponse<Void> delete(Long id) {
        gradeRepository.deleteById(id);
        return ApiResponse.success(null, "Grade deleted successfully");
    }

}
