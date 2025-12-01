package com.example.umc9th.domain.auth.converter;

import com.example.umc9th.domain.auth.dto.AuthResponseDto;
import com.example.umc9th.domain.user.entity.User;

public class AuthConverter {

    public static AuthResponseDto.LoginResultDto toLoginResultDto(User user, String accessToken, String refreshToken) {
        return AuthResponseDto.LoginResultDto.builder()
                .userId(user.getId())
                .accessToken(accessToken)
                .refreshToken(accessToken)
                .build();
    }
}