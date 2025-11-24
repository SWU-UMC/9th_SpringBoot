package com.example.umc9th.domain.member.converter;

import com.example.umc9th.domain.member.dto.MemberRequestDto;
import com.example.umc9th.domain.member.dto.MemberResponseDto;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberConverter {

    private final PasswordEncoder passwordEncoder;

    public Member toNormalMember(MemberRequestDto.SignUpRequest req) {

        return Member.builder()
                .email(req.getEmail())
                .password(passwordEncoder.encode(req.getPassword()))
                .nickname(req.getNickname())
                .gender(null)
                .birth(null)
                .role(Role.NORMAL)
                .socialId(null)
                .socialType(null)
                .build();
    }

    public Member toSocialMember(MemberRequestDto.SocialSignUpRequest req) {

        return Member.builder()
                .socialId(req.getSocialId())
                .socialType(req.getSocialType())
                .nickname(req.getNickname())
                .email(null)
                .password(null)
                .role(Role.NORMAL)
                .build();
    }

    public MemberResponseDto.SignUpResponse toSignUpResponse(Member member) {
        return MemberResponseDto.from(member);
    }
}
