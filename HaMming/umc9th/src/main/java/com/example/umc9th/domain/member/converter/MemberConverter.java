package com.example.umc9th.domain.member.converter;

import com.example.umc9th.domain.member.dto.MemberRequestDto;
import com.example.umc9th.domain.member.dto.MemberResponseDto;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.enums.Role;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MemberConverter {

    public Member toMember(MemberRequestDto.SignUpRequest request, String encodedPassword) {
        return Member.builder()
                .email(request.getEmail())
                .password(encodedPassword)
                .nickname(request.getNickname())
                .gender(request.getGender())
                .birth(request.getBirth())
                .role(Role.ROLE_USER)
                .build();
    }

    public MemberResponseDto.SignUpResponse toSignupResponse(Member member) {
        return MemberResponseDto.SignUpResponse.builder()
                .memberId(member.getId())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .gender(member.getGender())
                .birth(member.getBirth())
                .build();
    }

    public MemberResponseDto.LoginResponse toLoginResponse(Member member, String accessToken) {
        return MemberResponseDto.LoginResponse.builder()
                .memberId(member.getId())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .accessToken(accessToken)
                .build();
    }

    public static MemberResponseDto.KaKaoLoginResponse toKaKaoLoginResponse(Member member, String accessToken) {

        return MemberResponseDto.KaKaoLoginResponse.builder()
                .memberId(member.getId())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .accessToken(accessToken)
                .build();
    }


}
