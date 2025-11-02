package com.example.leeseo.domain.review.service;

import com.example.leeseo.domain.review.entity.QReview;
import com.example.leeseo.domain.review.entity.Review;
import com.example.leeseo.domain.review.repository.ReviewRepository;
import com.example.leeseo.domain.store.entity.QLocation;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {
    private final ReviewRepository reviewRepository;

    public List<Review> searchReview(
            String query, String type
    ){
        //Q클래스 정의
        QReview review = QReview.review;
        QLocation location = QLocation.location;

        //BooleanBuilder 정의
        BooleanBuilder builder = new BooleanBuilder();

        //동적 쿼리: 검색 조건
        if (type.equals("location")){
            builder.and(location.name.contains(query));
        }
        if (type.equals("rate")){
            builder.and(review.rate.goe(Float.parseFloat(query)));
        }
        if (type.equals("both")){
            String firstQuery = query.split("&")[0];
            String secondQuery = query.split("&")[1];

            builder.and(location.name.contains(firstQuery));
            builder.and(review.rate.goe(Float.parseFloat(secondQuery)));
        }

        //Repository 사용 & 결과 매핑
        List<Review> reviewList = reviewRepository.searchReview(builder);

        return reviewList;
    }
}
