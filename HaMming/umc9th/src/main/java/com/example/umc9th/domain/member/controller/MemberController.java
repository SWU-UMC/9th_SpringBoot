package com.example.umc9th.domain.member.controller;

import com.example.umc9th.domain.member.dto.MemberRequestDto;
import com.example.umc9th.domain.member.dto.MemberResponseDto;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.service.MemberService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final AuthenticationManager authenticationManager;

    // 1) 회원가입
    @Operation(summary = "회원가입 진행")
    @PostMapping("/signup")
    public ApiResponse<MemberResponseDto.SignUpResponse> signup(
            @RequestBody MemberRequestDto.SignUpRequest request
    ) {
        MemberResponseDto.SignUpResponse response = memberService.signup(request);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }

    // 2) 로그인 (세션 생성)
    @Operation(summary = "로그인 진행")
    @PostMapping("/login")
    public ApiResponse<MemberResponseDto.LoginResponse> login(
            @RequestBody MemberRequestDto.LoginRequest request,
            HttpServletRequest httpRequest
    ) {
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());

        Authentication authentication = authenticationManager.authenticate(authToken);

        // SecurityContext에 저장
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 세션에 SecurityContext 저장
        HttpSession session = httpRequest.getSession(true);
        session.setAttribute(
                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                SecurityContextHolder.getContext()
        );

        // 로그인한 사용자 정보 응답
        Member member = memberService.findByEmail(request.getEmail());
        MemberResponseDto.LoginResponse response = MemberResponseDto.LoginResponse.builder()
                .memberId(member.getId())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .build();

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }

    // 3) 로그아웃 (세션 무효화)
    @Operation(summary = "로그아웃 진행")
    @PostMapping("/logout")
    public ApiResponse<Void> logout(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        SecurityContextHolder.clearContext();

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, null);
    }
}
