package com.example.study_quiz_ai.modules.grade.service;

import java.util.List;

import com.example.study_quiz_ai.core.base.ApiResponse;
import com.example.study_quiz_ai.modules.grade.dto.CreateOrEditGradeDto;
import com.example.study_quiz_ai.modules.grade.dto.GetGradeForViewDto;

public interface GradeService {
    ApiResponse<CreateOrEditGradeDto> create(CreateOrEditGradeDto dto);

    ApiResponse<List<GetGradeForViewDto>> getAll();

    ApiResponse<GetGradeForViewDto> getById(Long id);

    ApiResponse<CreateOrEditGradeDto> update(Long id, CreateOrEditGradeDto dto);

    ApiResponse<Void> delete(Long id);

}
