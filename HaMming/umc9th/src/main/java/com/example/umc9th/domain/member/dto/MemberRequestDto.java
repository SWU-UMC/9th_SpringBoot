package com.example.umc9th.domain.member.dto;

import com.example.umc9th.domain.member.enums.Gender;
import com.example.umc9th.domain.member.enums.SocialType;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MemberRequestDto {

    @Getter
    @NoArgsConstructor
    public static class SignUpRequest {
        private String email;
        private String password;
        private String nickname;
        private Gender gender;     // 선택
        private String birth;      // 선택
    }

    @Getter
    @NoArgsConstructor
    public static class LoginRequest {
        private String email;
        private String password;
    }

    @Getter
    @NoArgsConstructor
    public static class SocialSignUpRequest {
        private String socialId;
        private SocialType socialType;
        private String nickname;

    }
}
