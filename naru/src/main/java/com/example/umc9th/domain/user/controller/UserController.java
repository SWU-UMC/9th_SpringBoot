package com.example.umc9th.domain.user.controller;

import com.example.umc9th.domain.user.dto.UserRequestDto;
import com.example.umc9th.domain.user.dto.UserResponseDto;
import com.example.umc9th.domain.user.entity.User;
import com.example.umc9th.domain.user.service.UserService;
import com.example.umc9th.global.entity.apiPayload.ApiResponse;
import com.example.umc9th.global.entity.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.umc9th.domain.user.converter.UserConverter.toJoinResultDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@Tag(name = "User API", description = "회원 관련 API")
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    @Operation(summary = "회원가입 API", description = "사용자 정보를 입력받아 회원가입을 진행합니다.")
    public ApiResponse<UserResponseDto.JoinResultDto> join(@RequestBody @Valid UserRequestDto.JoinDto request) {
        User user = userService.join(request);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, toJoinResultDto(user));
    }
}