package com.example.umc9th.domain.user.converter;

import com.example.umc9th.domain.user.dto.UserRequestDto;
import com.example.umc9th.domain.user.dto.UserResponseDto;
import com.example.umc9th.domain.user.entity.User;
import com.example.umc9th.domain.user.entity.enums.UserRole;

public class UserConverter {

    public static UserResponseDto.JoinResultDto toJoinResultDto(User user) {
        return UserResponseDto.JoinResultDto.builder()
                .userId(user.getId())
                .createdAt(user.getCreatedAt()) // BaseEntity의 createdAt
                .build();
    }

    public static User toEntity(UserRequestDto.JoinDto request, String encodedPassword) {
        return User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(encodedPassword)
                .gender(request.getGender())
                .birthday(request.getBirthday())
                .address(request.getAddress())
                .detailAddress(request.getDetailAddress())
                .phoneNumber(request.getPhoneNumber())
                .role(UserRole.USER) //
                .build();
    }
}