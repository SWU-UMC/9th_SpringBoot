package com.example.umc9th.domain.review.converter;

import com.example.umc9th.domain.review.dto.ReviewResponse;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.entity.Reply;
import org.springframework.stereotype.Component;

@Component
public class ReviewConverter {

    public ReviewResponse toReviewResponse(Review review) {
        Reply reply = review.getReply();

        return ReviewResponse.builder()
                .reviewId(review.getId())
                .reviewText(review.getReviewText())
                .rate(review.getRate())
                .storeId(review.getStore().getId())
                .storeName(review.getStore().getStoreName())
                .regionName(review.getStore().getRegion().getRegionName())
                .replyText(reply != null ? reply.getReplyText() : null)
                .createdAt(review.getCreatedAt())
                .build();
    }
}
