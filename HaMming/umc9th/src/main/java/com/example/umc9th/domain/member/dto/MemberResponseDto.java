package com.example.umc9th.domain.member.dto;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class MemberResponseDto {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class SignUpResponse {
        private Long memberId;
        private String email;
        private String nickname;
        private Gender gender;
        private String birth;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class LoginResponse {
        private Long memberId;
        private String email;
        private String nickname;
    }

}
