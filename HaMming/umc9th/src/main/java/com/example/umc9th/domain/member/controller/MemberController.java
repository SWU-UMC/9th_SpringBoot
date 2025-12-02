package com.example.umc9th.domain.auth.controller;

import com.example.umc9th.domain.member.converter.MemberConverter;
import com.example.umc9th.domain.member.dto.MemberRequestDto;
import com.example.umc9th.domain.member.dto.MemberResponseDto;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.service.MemberService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import com.example.umc9th.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    // 1) 회원가입 (DB 저장)
    @PostMapping("/signup")
    public ApiResponse<MemberResponseDto.SignUpResponse> signup(
            @RequestBody MemberRequestDto.SignUpRequest request
    ) {
        MemberResponseDto.SignUpResponse response = memberService.signup(request);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }

    // 2) 로그인 (JWT 발급)
    @PostMapping("/login")
    public ApiResponse<MemberResponseDto.LoginResponse> login(
            @RequestBody MemberRequestDto.LoginRequest request
    ) {
        // 이메일 + 비밀번호 검증
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());

        Authentication authentication = authenticationManager.authenticate(authToken);

        // JWT 생성
        String accessToken = jwtTokenProvider.createAccessToken(authentication);

        // 사용자 정보 조회
        Member member = memberService.findByEmail(request.getEmail());

        // 응답 DTO
        MemberResponseDto.LoginResponse response =
                MemberConverter.toLoginResponse(member, accessToken);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }
}
