package com.example.server_9th.service;

import com.example.server_9th.domain.QRegion;
import com.example.server_9th.domain.QStore;
import com.example.server_9th.domain.mapping.review.QReview;
import com.example.server_9th.domain.mapping.review.Review;
import com.example.server_9th.repository.ReviewRepo.ReviewRepository;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.server_9th.domain.QRegion.region;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {

    private final ReviewRepository reviewRepository;

    public List<Review> searchReview(String query, String type){

        //Q클래스 정의
        QReview review = QReview.review;
        QRegion region = QRegion.region;

        //BooleanBuilder 정의
        BooleanBuilder builder = new BooleanBuilder();

        //BooleanBuilder 사용

        //동적 쿼리: 검색 조건
        if (type.equals("region")) {
            builder.and(region.dong.contains(query));
        }
        if (type.equals("star")) {
            builder.and(review.rating.goe(Double.parseDouble(query)));
        }
        if (type.equals("both")) {
            String firstQuery = query.split("&")[0];
            String secondQuery = query.split("&")[1];
            builder.and(region.dong.contains(firstQuery));
            builder.and(review.rating.goe(Double.parseDouble(secondQuery)));
        }

        return reviewRepository.searchReview(builder);
    }
}
