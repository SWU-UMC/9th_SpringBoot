package com.example.server_9th.controller;

import com.example.server_9th.apiPayload.ApiResponse;
import com.example.server_9th.apiPayload.code.SuccessCode;
import com.example.server_9th.converter.ReviewConverter;
import com.example.server_9th.domain.mapping.review.Review;
import com.example.server_9th.dto.ReviewDto;
import com.example.server_9th.service.ReviewQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewQueryService reviewQueryService;

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<Review>>> searchReview(@RequestParam String query, @RequestParam String type){
        // 서비스에게 요청
        List<Review> result = reviewQueryService.searchReview(query,type);
        return  ResponseEntity.ok(ApiResponse.onSuccess(SuccessCode._OK, result));
    }

    @GetMapping("/myReview")
    public ResponseEntity<ApiResponse<List<ReviewDto.MyReviewResponseDto>>> getMyReviews(
            @RequestParam Long memberId,
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Double rating
    ){
        return ResponseEntity.ok(ApiResponse.onSuccess(SuccessCode._OK,reviewQueryService.getMyReviews(memberId, storeName, rating)));
    }
}
