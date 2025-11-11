package com.example.server_9th.converter;

import com.example.server_9th.domain.Member;
import com.example.server_9th.domain.Store;
import com.example.server_9th.domain.mapping.review.Review;
import com.example.server_9th.domain.mapping.review.ReviewId;
import com.example.server_9th.dto.ReviewDto;

import static com.example.server_9th.domain.QMember.member;

public class ReviewConverter {

    public static Review toEntity(ReviewDto.ReviewRequestDto request, Member member, Store store){

        ReviewId reviewId = new ReviewId(member.getId(), store.getStore_id());

        return Review.builder()
                .id(reviewId)
                .content(request.getContext())
                .rating(request.getRating())
                .imageUrls(request.getImageUrl())
                .build();
    }

    public static ReviewDto.MyReviewResponseDto toMyReviewResponseDto(Review review){
        return ReviewDto.MyReviewResponseDto.builder()
                .memberId(review.getId().getUser_id())
                .rating(review.getRating())
                .context(review.getContent())
                .imageUrl(review.getImageUrls())
                .dateTime(review.getCreatedAt())
                .build();
    }
}
