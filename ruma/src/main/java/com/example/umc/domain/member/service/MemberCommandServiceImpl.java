package com.example.umc.domain.member.service;

import com.example.umc.domain.member.converter.MemberConverter;
import com.example.umc.domain.member.dto.req.MemberReqDTO;
import com.example.umc.domain.member.dto.res.MemberResDTO;
import com.example.umc.domain.member.entity.Member;
import com.example.umc.domain.member.repository.MemberRepository;
import com.example.umc.domain.member.service.MemberService.MemberCommandService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public MemberResDTO.JoinDTO signup(MemberReqDTO.JoinDTO dto) {

        Member member = MemberConverter.toMember(dto);
        memberRepository.save(member);

        return MemberConverter.toJoinDTO(member);
    }
}

