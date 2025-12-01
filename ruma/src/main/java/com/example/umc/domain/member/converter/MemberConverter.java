package com.example.umc.domain.member.converter;

import com.example.umc.domain.member.dto.req.MemberReqDTO;
import com.example.umc.domain.member.dto.res.MemberResDTO;
import com.example.umc.domain.member.entity.Member;

public class MemberConverter {
    // DTO → Entity
    public static Member toMember(MemberReqDTO.JoinDTO dto){
        return Member.builder()
                .email(dto.email())
                .password(dto.password())
                .name(dto.name())
                .gender(dto.gender())
                .birthday(dto.birthday())
                .address(dto.address())
                .addressDetail(dto.addressDetail())
                .build();
    }

    // Entity → DTO
    public static MemberResDTO.JoinDTO toJoinDTO(Member member){
        return MemberResDTO.JoinDTO.builder()
                .memberId(member.getMemberId())
                .createdAt(member.getCreatedAt())
                .build();
    }
}
