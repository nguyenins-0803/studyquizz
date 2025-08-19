package com.example.study_quiz_ai.modules.auth.service;

import com.example.study_quiz_ai.core.base.ApiResponse;
import com.example.study_quiz_ai.modules.auth.dto.LoginDto;
import com.example.study_quiz_ai.modules.auth.dto.RegisterDto;
import com.example.study_quiz_ai.modules.role.exception.RoleNotFoundException;
import com.example.study_quiz_ai.modules.user.exception.UserAlreadyExistsException;

public interface AuthService {
    ApiResponse<?> register(RegisterDto request) throws UserAlreadyExistsException, RoleNotFoundException;

    ApiResponse<?> login(LoginDto request);
}
