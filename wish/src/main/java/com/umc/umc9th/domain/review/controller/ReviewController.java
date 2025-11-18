package com.umc.umc9th.domain.review.controller;

import com.umc.umc9th.domain.review.dto.MyReviewResponse;
import com.umc.umc9th.domain.review.service.ReviewService;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReviewController {

  private final ReviewService reviewService;

  @GetMapping("/mypage/reviews")
  public Map<String, Object> getMyReviews(
      @RequestParam(required = false) Integer storeId,
      @RequestParam(required = false) BigDecimal minRating,
      @RequestParam(required = false) BigDecimal maxRating,
      @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
  ) {
    Integer userId = 1;
    Page<MyReviewResponse> page = reviewService.getMyReviews(userId, storeId, minRating, maxRating, pageable);

    Map<String, Object> response = new HashMap<>();
    response.put("reviews", page.getContent());
    response.put("currentPage", page.getNumber());
    response.put("totalPages", page.getTotalPages());
    response.put("totalElements", page.getTotalElements());

    return response;
  }
}