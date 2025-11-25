package com.example.umc.domain.review.dto.req;

import java.util.List;
import jakarta.validation.constraints.*;

public class ReviewReqDTO {

    public record CreateDTO(
            @NotNull
            Long storeId,
            @NotNull(message = "rating은 필수 값입니다.")
            @Min(value = 0)
            @Max(value = 5)
            Integer rating,
            @NotBlank
            String content,
            List<String> photoUrls
    ) {}
}
