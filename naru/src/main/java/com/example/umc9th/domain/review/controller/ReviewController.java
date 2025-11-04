package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.ReviewResponseDto;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.service.ReviewQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewQueryService reviewQueryService;

    @GetMapping("/search")
    public List<Review> searchReview(
            @RequestParam String query,
            @RequestParam String type
    ){
        return reviewQueryService.searchReview(query,type);
    }

    @Operation(summary = "내 리뷰 조회", description = "로그인한 사용자의 리뷰를 조회합니다.")
    @GetMapping("/my")
    public List<ReviewResponseDto> getMyReviews(
            @Parameter(description = "로그인된 사용자 ID", example = "1")
            @RequestParam Long userId,
            @Parameter(description = "가게 이름 필터", example = "마라탕")
            @RequestParam(required = false) String storeName,
            @Parameter(description = "별점 그룹 (5,4,3 등)", example = "5")
            @RequestParam(required = false) Integer scoreGroup
    ) {
        return reviewQueryService.getMyReviews(userId, storeName, scoreGroup);
    }
}