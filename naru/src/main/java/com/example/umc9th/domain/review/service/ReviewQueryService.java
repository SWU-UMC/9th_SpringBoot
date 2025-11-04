package com.example.umc9th.domain.review.service;

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

    public List<Review> searchReview(String query, String type) {
        QReview review = QReview.review;

        BooleanBuilder builder = new BooleanBuilder();

        // 지역명 기반 검색 (Store -> Location.name)
        if ("location".equalsIgnoreCase(type)) {
            builder.and(review.store.location.name.containsIgnoreCase(query));
        }

        // 별점 기반 검색 (score)
        else if ("score".equalsIgnoreCase(type)) {
            try {
                double score = Double.parseDouble(query);
                builder.and(review.score.goe(score)); // 이상 검색
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("별점 검색 시 숫자 형태로 입력해야 합니다.");
            }
        }

        // 지역 + 별점 동시 검색 (location & score)
        else if ("both".equalsIgnoreCase(type)) {
            // 예시 query 형식: "서울&4.5"
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

        return reviewRepository.searchReview(builder);
    }
}
