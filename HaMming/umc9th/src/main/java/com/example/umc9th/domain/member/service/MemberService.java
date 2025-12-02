package com.example.umc9th.domain.member.service;

import com.example.umc9th.domain.member.converter.MemberConverter;
import com.example.umc9th.domain.member.dto.MemberRequestDto;
import com.example.umc9th.domain.member.dto.MemberResponseDto;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.enums.Role;
import com.example.umc9th.domain.member.enums.SocialType;
import com.example.umc9th.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

    //카카오
    public Member kakaoLogin(Long kakaoId, String email, String nickname) {

        String kakaoIdStr = String.valueOf(kakaoId);

        return memberRepository.findByKakaoId(kakaoIdStr)
                .orElseGet(() -> {
                    Member newMember = Member.builder()
                            .kakaoId(kakaoIdStr)
                            .email(email)
                            .nickname(nickname)
                            .role(Role.ROLE_USER)
                            .build();

                    return memberRepository.save(newMember);
                });
    }


}
