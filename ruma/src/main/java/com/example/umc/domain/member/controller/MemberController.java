package com.example.umc.domain.member.controller;

import com.example.umc.domain.member.dto.req.MemberReqDTO;
import com.example.umc.domain.member.dto.res.MemberResDTO;
import com.example.umc.domain.member.exception.code.MemberSuccessCode;
import com.example.umc.domain.member.service.MemberCommandService;
import com.example.umc.domain.member.service.MemberQueryService;
import com.example.umc.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
class MemberController {

    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;   // ★ 수정됨

    // 회원가입
    @PostMapping("/signup")
    public ApiResponse<MemberResDTO.JoinDTO> signUp(
            @RequestBody MemberReqDTO.JoinDTO dto
    ) {
        return ApiResponse.onSuccess(
                MemberSuccessCode.FOUND,
                memberCommandService.signup(dto)
        );
    }

    // 로그인
    @PostMapping("/login")
    public ApiResponse<MemberResDTO.LoginDTO> login(
            @RequestBody @Valid MemberReqDTO.LoginDTO dto
    ){
        return ApiResponse.onSuccess(
                MemberSuccessCode.FOUND,
                memberQueryService.login(dto)    // ★ 타입 정상, 빈 정상 주입
        );
    }
}
