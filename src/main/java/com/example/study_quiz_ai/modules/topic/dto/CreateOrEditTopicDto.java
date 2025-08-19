package com.example.study_quiz_ai.modules.topic.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Schema
@Getter
@Setter
public class CreateOrEditTopicDto {
    @NotNull
    private String name;

    @NotNull
    private String description;
}
