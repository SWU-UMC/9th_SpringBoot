package com.example.umc9th.domain.member.controller;

import com.example.umc9th.domain.member.dto.MemberRequestDto;
import com.example.umc9th.domain.member.dto.MemberResponseDto;
import com.example.umc9th.domain.member.service.MemberCommandService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberCommandService memberCommandService;

    /**
     * 1. 일반 회원가입
     */
    @PostMapping("/signup")
    public ApiResponse<MemberResponseDto.SignUpResponse> signUp(
            @RequestBody MemberRequestDto.SignUpRequest req
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.CREATED,
                MemberResponseDto.from(memberCommandService.signUp(req))
        );
    }

    /**
     * 2. 소셜 회원가입
     */
    @PostMapping("/social")
    public ApiResponse<MemberResponseDto.SignUpResponse> socialSignUp(
            @RequestBody MemberRequestDto.SocialSignUpRequest req
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.CREATED,
                MemberResponseDto.from(memberCommandService.socialSignUp(req))
        );
    }
}
