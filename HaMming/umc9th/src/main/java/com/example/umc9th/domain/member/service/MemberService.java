package com.example.umc9th.domain.member.service;

import com.example.umc9th.domain.member.converter.MemberConverter;
import com.example.umc9th.domain.member.dto.MemberRequestDto;
import com.example.umc9th.domain.member.dto.MemberResponseDto;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberResponseDto.SignUpResponse signup(MemberRequestDto.SignUpRequest request) {
        if (memberRepository.existsByEmail((request.getEmail()))) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
        }

        String encodedPw = passwordEncoder.encode(request.getPassword());
        Member member = MemberConverter.toMember(request, encodedPw);
        Member saved = memberRepository.save(member);

        return MemberConverter.toSignupResponse(saved);
    }

    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
    }
}
