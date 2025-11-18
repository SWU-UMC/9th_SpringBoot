package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.dto.ReviewResponseDto;
import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
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
                throw new IllegalArgumentException("별점 검색 시 숫자 형태로 입력해야 합니다.");
            }
        } else if ("both".equalsIgnoreCase(type)) {
            String[] splitQuery = query.split("&");
            if (splitQuery.length == 2) {
                String locationQuery = splitQuery[0];
                double scoreQuery = Double.parseDouble(splitQuery[1]);
                builder.and(review.store.location.name.containsIgnoreCase(locationQuery));
                builder.and(review.score.goe(scoreQuery));
            } else {
                throw new IllegalArgumentException("both 검색 시 '지역명&별점' 형식으로 입력해야 합니다.");
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
