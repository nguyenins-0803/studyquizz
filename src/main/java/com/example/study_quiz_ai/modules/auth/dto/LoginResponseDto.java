package com.example.study_quiz_ai.modules.auth.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Schema
@Data
@Builder
public class LoginResponseDto {
    private String token;

    @Builder.Default
    private String type = "Bearer";

    private Long id;

    private String username;

    private String email;

    private List<String> roles;
}
