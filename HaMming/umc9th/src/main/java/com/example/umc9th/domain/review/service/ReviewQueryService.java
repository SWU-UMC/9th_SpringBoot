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

        // 지역명 기반 검색
        if (type.equals("region")) {
            builder.and(review.store.region.regionName.contains(query));
        }

        // 별점 기반 검색
        if (type.equals("rate")) {
            builder.and(review.rate.goe(Integer.parseInt(query)));
        }

        // 둘 다 검색 (region + rate)
        if (type.equals("both")) {
            String[] splitQuery = query.split("&");
            builder.and(review.store.region.regionName.contains(splitQuery[0]));
            builder.and(review.rate.goe(Integer.parseInt(splitQuery[1])));
        }

        return reviewRepository.searchReview(builder);
    }
}

