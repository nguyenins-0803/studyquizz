package com.example.study_quiz_ai.modules.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema
@Getter
@Setter
public class GetUserDto {
    private String id;
    private String email;
    private String name;
}
