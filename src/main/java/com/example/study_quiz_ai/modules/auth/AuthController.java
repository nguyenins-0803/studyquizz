package com.example.study_quiz_ai.modules.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.study_quiz_ai.core.base.ApiResponse;
import com.example.study_quiz_ai.modules.auth.dto.LoginDto;
import com.example.study_quiz_ai.modules.auth.dto.RegisterDto;
import com.example.study_quiz_ai.modules.auth.service.AuthService;
import com.example.study_quiz_ai.modules.role.exception.RoleNotFoundException;
import com.example.study_quiz_ai.modules.user.exception.UserAlreadyExistsException;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<?>> register(@RequestBody @Valid RegisterDto entity)
            throws UserAlreadyExistsException, RoleNotFoundException {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(entity));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<?>> login(@RequestBody @Valid LoginDto entity, HttpServletResponse response) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.login(entity, response));
    }

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<?>> getMe(@CookieValue(name = "token", required = false) String token) {
        return authService.getMe(token);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletResponse response) {
        authService.logout(response);
        return ResponseEntity.ok().build();
    }
}
