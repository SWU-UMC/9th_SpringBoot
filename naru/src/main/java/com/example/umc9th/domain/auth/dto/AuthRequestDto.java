package com.example.umc9th.domain.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

public class AuthRequestDto {

    @Getter
    public static class LoginDto {
        @Schema(description = "이메일", example = "test@example.com")
        @NotBlank
        @Email
        private String email;

        @Schema(description = "비밀번호", example = "password123!")
        @NotBlank
        private String password;
    }
}