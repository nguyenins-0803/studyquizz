package com.example.study_quiz_ai.modules.subject.service;

import java.util.List;

import com.example.study_quiz_ai.core.base.ApiResponse;
import com.example.study_quiz_ai.modules.subject.dto.CreateOrEditSubjectDto;
import com.example.study_quiz_ai.modules.subject.dto.GetSubjectDto;

public interface SubjectService {
    ApiResponse<List<GetSubjectDto>> getAll();

    ApiResponse<GetSubjectDto> getById(Long id);

    ApiResponse<CreateOrEditSubjectDto> create(CreateOrEditSubjectDto dto);

    ApiResponse<CreateOrEditSubjectDto> update(Long id, CreateOrEditSubjectDto dto);

    ApiResponse<Void> delete(Long id);
}
