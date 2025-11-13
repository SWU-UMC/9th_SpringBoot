package com.example.leeseo.domain.member.converter;

import com.example.leeseo.domain.member.dto.MemberReqDTO;
import com.example.leeseo.domain.member.dto.MemberResDTO;
import com.example.leeseo.domain.member.entity.Member;


public class MemberConverter {

    // Entity -> DTO
    public static MemberResDTO.JoinDTO toJoinDTO(
            Member member
    ){
        return MemberResDTO.JoinDTO.builder()
                .memberId(member.getId())
                .createAt(member.getCreated_at())
                .build();
    }

    // DTO -> Entity
    public static Member toMember(
            MemberReqDTO.JoinDTO dto
    ){
        return Member.builder()
                .name(dto.name())
                .birth(dto.birth())
                .address(dto.address())
                .detail_address(dto.specAddress())
                .gender(dto.gender())
                .build();
    }
}
