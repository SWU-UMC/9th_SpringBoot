package com.example.umc9th.domain.member.service;

import com.example.umc9th.domain.member.dto.MemberRequestDto;
import com.example.umc9th.domain.member.entity.Member;

public interface MemberCommandService {

    Member signUp(MemberRequestDto.SignUpRequest req);

    Member socialSignUp(MemberRequestDto.SocialSignUpRequest req);
}
