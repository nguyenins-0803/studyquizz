package com.example.study_quiz_ai.modules.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema
@Data
@AllArgsConstructor
public class LoginDto {
    @Schema(example = "admin@gmail.com")
    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;

    @Schema(example = "Admin@123")
    @NotBlank(message = "Password is required")
    private String password;
}
