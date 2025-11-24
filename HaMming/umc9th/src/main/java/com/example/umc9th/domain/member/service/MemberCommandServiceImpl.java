package com.example.umc9th.domain.member.service;

import com.example.umc9th.domain.member.converter.MemberConverter;
import com.example.umc9th.domain.member.dto.MemberRequestDto;
import com.example.umc9th.domain.member.entity.Member;

import com.example.umc9th.domain.member.exception.code.MemberErrorCode;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;
    private final MemberConverter memberConverter;

    @Override
    public Member signUp(MemberRequestDto.SignUpRequest req) {

        if (memberRepository.findByEmail(req.getEmail()).isPresent())
            throw new GeneralException(MemberErrorCode.EMAIL_DUPLICATED);

        if (memberRepository.findByNickname(req.getNickname()).isPresent())
            throw new GeneralException(MemberErrorCode.NICKNAME_DUPLICATED);

        Member member = memberConverter.toNormalMember(req);
        return memberRepository.save(member);
    }

    @Override
    public Member socialSignUp(MemberRequestDto.SocialSignUpRequest req) {

        memberRepository.findByNickname(req.getNickname())
                .ifPresent(m -> { throw new GeneralException(MemberErrorCode.NICKNAME_DUPLICATED); });

        Member member = memberConverter.toSocialMember(req);
        return memberRepository.save(member);
    }
}
