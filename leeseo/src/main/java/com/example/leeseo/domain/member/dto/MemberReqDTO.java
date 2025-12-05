package com.example.leeseo.domain.member.dto;

import com.example.leeseo.domain.member.enums.Address;
import com.example.leeseo.domain.member.enums.Gender;
import com.example.leeseo.global.annotation.ExistFoods;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {

    @Schema(name = "MemberRequest")
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
            LocalDate birth,
            @NotNull
            Address address,
            @NotNull
            String specAddress,
            @ExistFoods
            List<Long> preferCategory
    ){}

    public record LoginDTO(
            @NotBlank
            String email,
            @NotBlank
            String password
    ){}
}
