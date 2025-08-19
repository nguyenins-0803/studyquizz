package com.example.study_quiz_ai.modules.auth.dto;

import com.example.study_quiz_ai.modules.user.dto.GetUserDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema
@Getter
@Setter
public class RegisterResponseDto {
    private GetUserDto user;
}
