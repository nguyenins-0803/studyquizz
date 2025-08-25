package com.example.study_quiz_ai.modules.subject.dto;

import com.google.auto.value.AutoValue.Builder;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetSubjectDto {
    private Long id;
    private String name;
}
