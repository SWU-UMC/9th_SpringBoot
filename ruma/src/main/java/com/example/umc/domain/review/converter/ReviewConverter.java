package com.example.umc.domain.review.converter;

import com.example.umc.domain.review.dto.res.ReviewResDTO;
import com.example.umc.domain.review.dto.req.ReviewReqDTO;
import com.example.umc.domain.review.entity.Review;
import com.example.umc.domain.review.entity.ReviewPhoto;
import com.example.umc.domain.member.entity.Member;
import com.example.umc.domain.store.entity.Store;

import java.util.List;

public class ReviewConverter {

    public static Review toReview(ReviewReqDTO.CreateDTO dto, Member member, Store store) {
        return Review.builder()
                .member(member)
                .store(store)
                .rating(dto.rating())
                .content(dto.content())
                .build();
    }

    public static List<ReviewPhoto> toReviewPhotoList(List<String> urls, Review review) {
        return urls.stream()
                .map(url -> ReviewPhoto.builder()
                        .review(review)
                        .photoUrl(url)
                        .build())
                .toList();
    }

    public static ReviewResDTO.CreateDTO toCreateDTO(Review review) {
        return ReviewResDTO.CreateDTO.builder()
                .reviewId(review.getReviewId())
                .storeId(review.getStore().getStoreId())
                .memberId(review.getMember().getMemberId())
                .rating(review.getRating())
                .content(review.getContent())
                .createdAt(review.getCreatedAt())
                .build();
    }
}
