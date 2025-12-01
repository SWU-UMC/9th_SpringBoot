package com.example.umc.domain.member.controller;

import com.example.umc.domain.member.dto.req.MemberReqDTO;
import com.example.umc.domain.member.dto.res.MemberResDTO;
import com.example.umc.domain.member.exception.code.MemberSuccessCode;
import com.example.umc.domain.member.service.MemberService;
import com.example.umc.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
class MemberController {

    private final MemberService.MemberCommandService memberCommandService;

    @PostMapping("/signup")
    public ApiResponse<MemberResDTO.JoinDTO> signUp(
            @RequestBody MemberReqDTO.JoinDTO dto
    ) {
        return ApiResponse.onSuccess(
                MemberSuccessCode.FOUND,
                memberCommandService.signup(dto)
        );
    }
}

