package com.example.leeseo.domain.review.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class ReviewReqDTO {

    public record JoinDTO(
        @NotBlank
        String content,
        @NotNull
        float rate,
        @NotNull
        List<String> img_url
    ){
    }
}
