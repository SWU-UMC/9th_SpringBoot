package com.example.server_9th.service.member;

import com.example.server_9th.converter.MemberConverter;
import com.example.server_9th.domain.Member;
import com.example.server_9th.domain.enums.Role;
import com.example.server_9th.dto.MemberReqDTO;
import com.example.server_9th.dto.MemberResDTO;
import com.example.server_9th.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional // 트랜잭션 관리를 위해 필수
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository; // DB 저장을 위해 필요
    private final PasswordEncoder passwordEncoder;

    @Override
    public MemberResDTO.JoinDTO signup(MemberReqDTO.JoinDTO dto) {

        // 1. 비밀번호 암호화
        // passwordEncoder.encode()는 salt가 포함된 '암호화된 비밀번호(String)'를 반환합니다.
        String encodedPassword = passwordEncoder.encode(dto.password());

        // 2. DTO -> Entity 변환 (컨버터 사용)
        Member member = MemberConverter.toMember(dto, encodedPassword, Role.ROLE_USER);

        // 3. DB 저장 (이 부분이 생략되어 있었습니다)
        Member savedMember = memberRepository.save(member);

        // 4. Entity -> Response DTO 변환 및 반환
        return MemberConverter.toJoinResultDTO(savedMember);
    }
}