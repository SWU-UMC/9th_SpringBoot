package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.ReviewResponse;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.service.ReviewQueryService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import com.example.umc9th.global.resolver.PageParam;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewQueryService reviewQueryService;

    // 내가 작성한 리뷰 목록 페이징 조회
    @Operation(summary = "내 리뷰 페이징 조회", description = "page는 반드시 1 이상의 값이어야 합니다.")
    @GetMapping("/my/paged")
    public ApiResponse<List<ReviewResponse>> getMyReviewsPaged(
            @RequestParam Long memberId,
            @PageParam Integer page
    ) {

        List<ReviewResponse> result = reviewQueryService.findMyReviewsPaged(memberId, page)
                .stream()
                .map(ReviewResponse::from)
                .toList();

        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                result
        );
    }
}
