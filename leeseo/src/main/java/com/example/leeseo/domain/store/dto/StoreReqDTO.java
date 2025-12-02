package com.example.leeseo.domain.store.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class StoreReqDTO {

    @Schema(name = "storeRequest")
    public record JoinDTO(
          @NotBlank
          String name,
          @NotNull
          Long managerNumber,
          @NotBlank
          String detailAddress
    ){
    }
}