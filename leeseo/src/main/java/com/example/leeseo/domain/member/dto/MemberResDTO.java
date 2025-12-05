package com.example.leeseo.domain.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;

public class MemberResDTO {

    @Builder
    @Schema(name = "MemberResponse")
    public record JoinDTO(
            Long memberId,
            LocalDateTime createAt
    ){}

    @Builder
    public record LoginDTO(
            Long memberId,
            String accessToken
    ){}
}