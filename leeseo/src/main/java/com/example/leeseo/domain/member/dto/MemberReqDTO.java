package com.example.leeseo.domain.member.dto;

import com.example.leeseo.domain.member.enums.Address;
import com.example.leeseo.domain.member.enums.Gender;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {

    public record JoinDTO(
            String name,
            Gender gender,
            LocalDate birth,
            Address address,
            String specAddress,
            List<Long> preferCategory
    ){}
}
