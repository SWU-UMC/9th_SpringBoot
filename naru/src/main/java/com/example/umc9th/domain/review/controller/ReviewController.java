package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.service.ReviewQueryService;
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

    /**
     * 내가 작성한 리뷰 보기 API
     *
     * @param userId 로그인된 사용자 id (임시 파라미터)
     * @param storeName 필터: 가게 이름 (예: 반이학생마라탕마라반)
     * @param scoreGroup 필터: 별점 그룹 (예: 5, 4, 3)
     */
    @GetMapping("/my")
    public List<Review> getMyReviews(
            @RequestParam Long userId,
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Integer scoreGroup
    ) {
        return reviewQueryService.getMyReviews(userId, storeName, scoreGroup);
    }
}