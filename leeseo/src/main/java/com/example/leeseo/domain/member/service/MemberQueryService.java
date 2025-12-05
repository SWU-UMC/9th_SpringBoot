package com.example.leeseo.domain.member.service;

import com.example.leeseo.domain.member.converter.MemberConverter;
import com.example.leeseo.domain.member.dto.MemberReqDTO;
import com.example.leeseo.domain.member.dto.MemberResDTO;
import com.example.leeseo.domain.member.entity.CustomUserDetails;
import com.example.leeseo.domain.member.entity.Member;
import com.example.leeseo.domain.member.exception.MemberException;
import com.example.leeseo.domain.member.exception.code.MemberErrorCode;
import com.example.leeseo.domain.member.repository.MemberRepository;
import com.example.leeseo.global.jwt.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberQueryService {

    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder encoder;

    public MemberResDTO.LoginDTO login(
            MemberReqDTO.@Valid LoginDTO dto
    ) {
        Member member = memberRepository.findByEmail(dto.email())
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        if (!encoder.matches(dto.password(),member.getPassword())){
            throw new MemberException(MemberErrorCode.INVALID_PW);
        }

        CustomUserDetails userDetails = new CustomUserDetails(member);
        String accessToken = jwtUtil.createAccessToken(userDetails);

        return MemberConverter.toLoginDTO(member, accessToken);
    }
}
