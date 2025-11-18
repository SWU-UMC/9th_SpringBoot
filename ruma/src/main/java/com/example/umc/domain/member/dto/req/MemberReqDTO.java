package com.example.umc.domain.member.dto.req;

import com.example.umc.domain.member.enums.Gender;

import java.time.LocalDate;

public class MemberReqDTO {
    public record JoinDTO(
            String email,
            String password,
            String name,
            Gender gender,
            LocalDate birthday,
            String address,
            String addressDetail
    ) {}
}
