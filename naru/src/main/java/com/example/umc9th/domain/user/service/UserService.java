package com.example.umc9th.domain.user.service;

import com.example.umc9th.domain.user.dto.UserRequestDto;
import com.example.umc9th.domain.user.entity.User;

public interface UserService {
    User join(UserRequestDto.JoinDto request);
}