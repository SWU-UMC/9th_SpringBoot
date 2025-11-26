package com.example.leeseo.domain.store.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;

public class StoreResDTO {

    @Builder
    @Schema(name = "StoreResponse")
    public record JoinDTO(
        Long store_id,
        LocalDateTime createdAt
    ){
    }
}
