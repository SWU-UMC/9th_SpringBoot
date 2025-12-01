package com.example.umc.domain.member.dto.req;

import com.example.umc.domain.member.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class MemberReqDTO {
    public record JoinDTO(
            @NotBlank
            String name,
            @Email
            String email,
            @NotBlank
            String password,
            @NotNull
            Gender gender,
            @NotNull
            LocalDate birthday,
            @NotNull
            String address,
            @NotNull
            String addressDetail
    ) {}
}
