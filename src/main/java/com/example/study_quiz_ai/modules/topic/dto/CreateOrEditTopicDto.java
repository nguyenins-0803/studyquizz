package com.example.study_quiz_ai.modules.topic.dto;

import java.util.Set;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Schema
@Getter
@Setter
public class CreateOrEditTopicDto {
    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotEmpty
    private Set<Long> subjectIds;
}
