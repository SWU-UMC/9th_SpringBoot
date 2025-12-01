package com.example.umc9th.domain.auth.service;

import com.example.umc9th.domain.auth.converter.AuthConverter;
import com.example.umc9th.domain.auth.dto.AuthRequestDto;
import com.example.umc9th.domain.auth.dto.AuthResponseDto;
import com.example.umc9th.domain.user.entity.User;
import com.example.umc9th.domain.user.repository.UserRepository;
import com.example.umc9th.domain.user.error.UserErrorCode;
import com.example.umc9th.global.entity.apiPayload.exception.GeneralException;
import com.example.umc9th.global.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    @Transactional
    public AuthResponseDto.LoginResultDto login(AuthRequestDto.LoginDto request) {
        // 1. 이메일로 유저 조회
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new GeneralException(UserErrorCode.USER_NOT_FOUND));

        // 2. 비밀번호 일치 확인
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new GeneralException(UserErrorCode.PASSWORD_NOT_MATCH);
        }

        // 3. 토큰 생성
        String accessToken = jwtTokenProvider.createToken(user.getEmail(), user.getRole().name());

        // 4. 응답 DTO 반환
        return AuthConverter.toLoginResultDto(user, accessToken);
    }
}