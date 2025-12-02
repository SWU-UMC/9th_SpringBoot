package com.example.leeseo.domain.review.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

import java.util.List;

public class ReviewReqDTO {

    @Schema(name = "ReviewRequest")
    public record JoinDTO(
        @NotBlank
        @Size(min = 1, max = 80)
        String content,
        @NotNull
        @Range(min = 0, max = 5)
        float rate,
        @NotNull
        List<String> imgUrl
    ){
    }
}
