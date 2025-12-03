package com.example.umc9th.domain.auth.service;

import com.example.umc9th.domain.auth.converter.AuthConverter;
import com.example.umc9th.domain.auth.dto.AuthRequestDto;
import com.example.umc9th.domain.auth.dto.AuthResponseDto;
import com.example.umc9th.domain.auth.entity.RefreshToken;
import com.example.umc9th.domain.auth.repository.RefreshTokenRepository;
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
    private final RefreshTokenRepository refreshTokenRepository;
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
        String accessToken = jwtTokenProvider.createAccessToken(user.getEmail(), user.getRole().name());
        String refreshToken = jwtTokenProvider.createRefreshToken(user.getEmail());

        // 4. Refresh Token 저장 (기존에 있다면 업데이트)
        RefreshToken rt = refreshTokenRepository.findByUserId(user.getId())
                .orElse(RefreshToken.builder()
                        .userId(user.getId())
                        .token(refreshToken)
                        .build());

        rt.updateToken(refreshToken);
        refreshTokenRepository.save(rt);

        // 5. 응답 DTO 반환
        return AuthConverter.toLoginResultDto(user, accessToken, refreshToken);
    }

    // 토큰 재발급
    @Transactional
    public AuthResponseDto.LoginResultDto reissue(String refreshToken) {
        // 1. Refresh Token 유효성 검증
        if (!jwtTokenProvider.validateToken(refreshToken)) {
            throw new GeneralException(UserErrorCode.INVALID_REFRESH_TOKEN);
        }

        // 2. 토큰 내 정보로 유저 찾기
        String email = jwtTokenProvider.getEmail(refreshToken);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new GeneralException(UserErrorCode.USER_NOT_FOUND));

        // 3. DB에 저장된 토큰과 비교
        RefreshToken storedToken = refreshTokenRepository.findByUserId(user.getId())
                .orElseThrow(() -> new GeneralException(UserErrorCode.REFRESH_TOKEN_NOT_FOUND));

        if (!storedToken.getToken().equals(refreshToken)) {
            throw new GeneralException(UserErrorCode.INVALID_REFRESH_TOKEN);
        }

        // 4. 새 Access Token 발급
        String newAccessToken = jwtTokenProvider.createAccessToken(user.getEmail(), user.getRole().name());

        // 5. 새 Refresh Token 발급 및 DB 업데이트 (RTR 적용)
        String newRefreshToken = jwtTokenProvider.createRefreshToken(user.getEmail());
        storedToken.updateToken(newRefreshToken); // Dirty Checking으로 자동 저장됨

        // 6. 둘 다 새로 반환
        return AuthConverter.toLoginResultDto(user, newAccessToken, newRefreshToken);
    }
}