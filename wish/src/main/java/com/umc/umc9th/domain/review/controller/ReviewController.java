package com.umc.umc9th.domain.review.controller;

import com.umc.umc9th.domain.review.dto.MyReviewResponse;
import com.umc.umc9th.domain.review.dto.ReviewReqDTO;
import com.umc.umc9th.domain.review.dto.ReviewResDTO;
import com.umc.umc9th.domain.review.exception.ReviewSuccessCode;
import com.umc.umc9th.domain.review.service.ReviewService;
import com.umc.umc9th.global.apiPayload.ApiResponse;
import com.umc.umc9th.global.apiPayload.code.GeneralSuccessCode;
import com.umc.umc9th.global.validation.annotation.CheckPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReviewController {

  private final ReviewService reviewService;

  @Operation(
      summary = "내가 작성한 리뷰 목록 조회 API",
      description = "사용자가 작성한 리뷰를 페이징 처리하여 조회합니다. 가게별, 별점별 필터링이 가능합니다."
  )
  @ApiResponses({
      @io.swagger.v3.oas.annotations.responses.ApiResponse(
          responseCode = "200",
          description = "리뷰 목록 조회 성공"
      ),
      @io.swagger.v3.oas.annotations.responses.ApiResponse(
          responseCode = "400",
          description = "잘못된 요청 (잘못된 페이지 번호, 별점 범위 등)"
      )
  })
  @GetMapping("/mypage/reviews")
  public ApiResponse<ReviewResDTO.MyReviewListDTO> getMyReviews(
      @RequestParam(required = false) Integer storeId,
      @RequestParam(required = false) BigDecimal minRating,
      @RequestParam(required = false) BigDecimal maxRating,
      @RequestParam(defaultValue = "1") @CheckPage Integer page
  ) {

    Integer userId = 1;

    Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("createdAt").descending());

    ReviewResDTO.MyReviewListDTO result =
        reviewService.getMyReviews(userId, storeId, minRating, maxRating, pageable);

    return ApiResponse.onSuccess(ReviewSuccessCode.FOUND, result);
  }

  /**
   * 리뷰 생성
   */
  @PostMapping("/mypage/reviews")
  public ApiResponse<ReviewResDTO.CreateDTO> createReview(
      @RequestHeader("Authorization") String authHeader,
      @RequestBody ReviewReqDTO.CreateDTO dto
  ) {
    Integer userId = 1;

    return ApiResponse.onSuccess(
        ReviewSuccessCode.CREATED,
        reviewService.createReview(userId, dto)
    );
  }
}
