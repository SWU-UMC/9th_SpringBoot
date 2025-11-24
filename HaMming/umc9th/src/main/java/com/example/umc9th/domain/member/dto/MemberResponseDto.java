package com.example.umc9th.domain.member.dto;

import com.example.umc9th.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;

public class MemberResponseDto {

    @Getter
    @Builder
    public static class SignUpResponse {
        private Long memberId;
        private String email;
        private String nickname;
        private String gender;
        private String birth;
        private String userAddress;
        private String phoneNumber;
        private String socialType;
    }

    public static SignUpResponse from(Member member) {
        return SignUpResponse.builder()
                .memberId(member.getId())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .gender(member.getGender() != null ? member.getGender().name() : null)
                .birth(member.getBirth())
                .userAddress(member.getUserAddress())
                .phoneNumber(member.getPhoneNumber())
                .socialType(member.getSocialType() != null ? member.getSocialType().name() : null)
                .build();
    }
}
