package com.example.umc.domain.review.controller;

import com.example.umc.domain.review.dto.QReviewDto;
import com.example.umc.domain.review.dto.res.ReviewPreViewListDTO;
import com.example.umc.domain.review.dto.res.ReviewResDTO;
import com.example.umc.domain.review.dto.req.ReviewReqDTO;
import com.example.umc.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc.domain.review.service.ReviewCommandService;
import com.example.umc.domain.review.service.ReviewQueryService;
import com.example.umc.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewQueryService reviewQueryService;
    private final ReviewCommandService reviewCommandService;

    @GetMapping("/my")
    public List<QReviewDto> getMyReviews(
            @RequestParam(required = false) Long memberId,
            @RequestParam(required = false) Long storeId,
            @RequestParam(required = false) Integer starRange,
            @RequestParam(required = false) Long cursorId,
            @RequestParam(required = false) String query,
            @RequestParam(required = false) String type
    ) {

        if (memberId != null) {
            return reviewQueryService.getMyReviews(memberId, storeId, starRange, cursorId);
        }

        if (query != null && type != null) {
            return reviewQueryService.searchReviews(query, type);
        }

        return List.of();
    }

    @PostMapping
    public ApiResponse<ReviewResDTO.CreateDTO> createReview(
            @RequestBody ReviewReqDTO.CreateDTO dto
    ) {
        return ApiResponse.onSuccess(
                ReviewSuccessCode.CREATED,
                reviewCommandService.createReview(dto)
        );
    }

    @GetMapping
    public ApiResponse<ReviewPreViewListDTO> getReviews(
            @RequestParam Long memberId,
            @RequestParam(defaultValue = "1") Integer page
    ) {
        var result = reviewQueryService.getMemberReviewList(memberId, page);

        return ApiResponse.onSuccess(
                ReviewSuccessCode.CREATED,
                result
        );
    }
}
