package com.example.server_9th.converter;

import com.example.server_9th.domain.Member;
import com.example.server_9th.domain.enums.Role;
import com.example.server_9th.dto.MemberReqDTO;
import com.example.server_9th.dto.MemberResDTO;

import java.time.LocalDateTime;

public class MemberConverter {
    // DTO, Salted Password, Role -> Entity
    public static Member toMember(
            MemberReqDTO.JoinDTO dto,
            String password,
            Role role
    ){
        return Member.builder()
                .name(dto.name())
                .email(dto.email()) // 추가된 코드
                .password(password) // 추가된 코드
                .role(role)         // 추가된 코드
                .gender(dto.gender())
                .build();
    }

    // Entity -> Response DTO (회원가입 완료 후 응답)
    public static MemberResDTO.JoinDTO toJoinResultDTO(Member member){
        return MemberResDTO.JoinDTO.builder()
                .memberId(member.getId())
                .createdAt(LocalDateTime.now()) // 혹은 member.getCreatedAt()
                .build();
    }
}
