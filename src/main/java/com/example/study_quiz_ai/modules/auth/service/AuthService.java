package com.example.study_quiz_ai.modules.auth.service;

import org.springframework.http.ResponseEntity;

import com.example.study_quiz_ai.core.base.ApiResponse;
import com.example.study_quiz_ai.modules.auth.dto.LoginDto;
import com.example.study_quiz_ai.modules.auth.dto.RegisterDto;
import com.example.study_quiz_ai.modules.role.exception.RoleNotFoundException;
import com.example.study_quiz_ai.modules.user.exception.UserAlreadyExistsException;

import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {
    ApiResponse<?> register(RegisterDto request) throws UserAlreadyExistsException, RoleNotFoundException;

    ApiResponse<?> login(LoginDto request, HttpServletResponse response);

    ResponseEntity<ApiResponse<?>> getMe(String token);

    void logout(HttpServletResponse response);
}
