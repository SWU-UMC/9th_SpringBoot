package com.example.umc9th.domain.user.dto;

import com.example.umc9th.domain.user.entity.enums.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

public class UserRequestDto {

    @Getter
    @Schema(description = "회원가입 요청 DTO")
    public static class JoinDto {
        @Schema(description = "사용자 이름", example = "홍길동")
        @NotBlank
        private String name;

        @Schema(description = "이메일", example = "test@example.com")
        @NotBlank
        @Email
        private String email;

        @Schema(description = "비밀번호", example = "password123!")
        @NotBlank
        private String password;

        @Schema(description = "성별", example = "MALE")
        @NotNull
        private Gender gender;

        @Schema(description = "생년월일", example = "2000-01-01")
        private LocalDate birthday;

        @Schema(description = "주소", example = "서울시 동작구")
        private String address;

        @Schema(description = "상세 주소", example = "101호")
        private String detailAddress;

        @Schema(description = "전화번호", example = "010-1234-5678")
        private String phoneNumber;
    }
}