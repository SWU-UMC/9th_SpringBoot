package com.example.umc9th.domain.user.service;

import com.example.umc9th.domain.user.converter.UserConverter;
import com.example.umc9th.domain.user.dto.UserRequestDto;
import com.example.umc9th.domain.user.entity.User;
import com.example.umc9th.domain.user.error.UserErrorCode;
import com.example.umc9th.domain.user.repository.UserRepository;
import com.example.umc9th.global.entity.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User join(UserRequestDto.JoinDto request) {
        // 1. 중복 체크
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new GeneralException(UserErrorCode.USER_ALREADY_EXIST);
        }

        // 2. 암호화 및 Entity 변환
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        User newUser = UserConverter.toEntity(request, encodedPassword);

        return userRepository.save(newUser);
    }
}