package com.example.study_quiz_ai.modules.topic.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema
@Getter
@Setter
public class GetTopicDto {
    private Long id;
    private String name;
    private String description;
}
