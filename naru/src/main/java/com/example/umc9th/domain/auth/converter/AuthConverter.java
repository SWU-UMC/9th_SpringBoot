package com.example.umc9th.domain.auth.converter;

import com.example.umc9th.domain.auth.dto.AuthResponseDto;
import com.example.umc9th.domain.user.entity.User;

import java.time.LocalDateTime;

public class AuthConverter {

    public static AuthResponseDto.LoginResultDto toLoginResultDto(User user, String accessToken) {
        return AuthResponseDto.LoginResultDto.builder()
                .userId(user.getId())
                .accessToken(accessToken)
                .build();
    }
}