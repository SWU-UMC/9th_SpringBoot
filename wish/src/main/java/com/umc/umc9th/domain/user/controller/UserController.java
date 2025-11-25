package com.umc.umc9th.domain.user.controller;

import com.umc.umc9th.domain.user.dto.UserReqDTO;
import com.umc.umc9th.domain.user.dto.UserResDTO;
import com.umc.umc9th.domain.user.dto.UserResDTO.JoinDTO;
import com.umc.umc9th.domain.user.exception.UserSuccessCode;
import com.umc.umc9th.domain.user.service.UserService;
import com.umc.umc9th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

  private final UserService userService;

  // 회원가입
  @PostMapping("/signup")
  public ApiResponse<JoinDTO> signUp(
      @RequestBody UserReqDTO.JoinDTO dto
  ) {
    return ApiResponse.onSuccess(
        UserSuccessCode.CREATED,
        userService.signup(dto)
    );
  }
}
