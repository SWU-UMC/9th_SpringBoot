package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.dto.ReviewResponseDto;
import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.global.entity.apiPayload.code.GeneralErrorCode;
import com.example.umc9th.global.entity.apiPayload.exception.GeneralException;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {

    private final ReviewRepository reviewRepository;

    public List<ReviewResponseDto> searchReview(String query, String type) {
        QReview review = QReview.review;
        BooleanBuilder builder = new BooleanBuilder();

        if ("location".equalsIgnoreCase(type)) {
            builder.and(review.store.location.name.containsIgnoreCase(query));
        } else if ("score".equalsIgnoreCase(type)) {
            try {
                double score = Double.parseDouble(query);
                builder.and(review.score.goe(score));
            } catch (NumberFormatException e) {
                // IllegalArgumentException 대신 GeneralException 사용
                throw new GeneralException(GeneralErrorCode.BAD_REQUEST);
            }
        } else if ("both".equalsIgnoreCase(type)) {
            String[] splitQuery = query.split("&");
            if (splitQuery.length == 2) {
                try {
                    String locationQuery = splitQuery[0];
                    double scoreQuery = Double.parseDouble(splitQuery[1]);
                    builder.and(review.store.location.name.containsIgnoreCase(locationQuery));
                    builder.and(review.score.goe(scoreQuery));
                } catch (NumberFormatException e) {
                    // 별점 부분 파싱 오류 처리
                    throw new GeneralException(GeneralErrorCode.BAD_REQUEST);
                }
            } else {
                // 형식 오류 처리
                throw new GeneralException(GeneralErrorCode.BAD_REQUEST);
            }
        }

        return ReviewConverter.toDtoList(reviewRepository.searchReview(builder));
    }


    /**
     * 내가 작성한 리뷰 보기 (가게명, 별점 필터 가능)
     */
    public List<ReviewResponseDto> getMyReviews(Long userId, String storeName, Integer scoreGroup) {
        QReview review = QReview.review;
        BooleanBuilder builder = new BooleanBuilder();

        builder.and(review.user.id.eq(userId));

        if (storeName != null && !storeName.isBlank()) {
            builder.and(review.store.name.containsIgnoreCase(storeName));
        }

        if (scoreGroup != null) {
            double minScore = scoreGroup;
            double maxScore = scoreGroup + 0.9;
            builder.and(review.score.between(minScore, maxScore));
        }

        // Converter 사용
        return ReviewConverter.toDtoList(reviewRepository.searchReview(builder));
    }
}