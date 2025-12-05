package com.example.leeseo.domain.member.controller;

import com.example.leeseo.domain.member.dto.MemberReqDTO;
import com.example.leeseo.domain.member.dto.MemberResDTO;
import com.example.leeseo.domain.member.exception.code.MemberSuccessCode;
import com.example.leeseo.domain.member.service.MemberCommandService;
import com.example.leeseo.domain.member.service.MemberQueryService;
import com.example.leeseo.global.entity.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;

    @PostMapping("/sign-up")
    public ApiResponse<MemberResDTO.JoinDTO> signUp(
            @Valid @RequestBody MemberReqDTO.JoinDTO dto
    ){
        return ApiResponse.onSuccess(MemberSuccessCode.FOUND, memberCommandService.signUp(dto));
    }

    @PostMapping("/login")
    public ApiResponse<MemberResDTO.LoginDTO> login(
            @Valid @RequestBody MemberReqDTO.LoginDTO dto
    ){
        return ApiResponse.onSuccess(MemberSuccessCode.FOUND, memberQueryService.login(dto));
    }
}
