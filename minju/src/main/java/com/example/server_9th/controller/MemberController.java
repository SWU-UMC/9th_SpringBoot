package com.example.server_9th.controller;

import com.example.server_9th.apiPayload.ApiResponse;
import com.example.server_9th.dto.MemberReqDTO;
import com.example.server_9th.dto.MemberResDTO;
import com.example.server_9th.service.member.MemberCommandService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.server_9th.apiPayload.code.SuccessCode._OK;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberCommandService memberCommandService;

    // 회원가입
    @PostMapping("/sign-up")
    public ApiResponse<MemberResDTO.JoinDTO> signUp(
            @RequestBody @Valid MemberReqDTO.JoinDTO dto
    ){
        return ApiResponse.onSuccess(_OK, memberCommandService.signup(dto));
    }

    // 로그인
    @PostMapping("/login")
    public ApiResponse<MemberResDTO.LoginDTO> login(
            @RequestBody @Valid MemberReqDTO.LoginDTO dto
    ){
        return ApiResponse.onSuccess(_OK, memberCommandService.login(dto));
    }

}
