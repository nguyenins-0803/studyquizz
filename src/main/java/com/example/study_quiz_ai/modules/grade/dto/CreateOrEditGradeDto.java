package com.example.study_quiz_ai.modules.grade.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Schema
@Getter
@Setter
public class CreateOrEditGradeDto {
    @NotBlank
    private String name;
}
