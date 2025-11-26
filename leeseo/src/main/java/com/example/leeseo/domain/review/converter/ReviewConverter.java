package com.example.leeseo.domain.review.converter;

import com.example.leeseo.domain.member.entity.Member;
import com.example.leeseo.domain.review.dto.ReviewReqDTO;
import com.example.leeseo.domain.review.dto.ReviewResDTO;
import com.example.leeseo.domain.review.entity.Review;
import com.example.leeseo.domain.store.entity.Store;

public class ReviewConverter {

    public static ReviewResDTO.JoinDTO toJoinDTO(
        Review review
    ){
        return ReviewResDTO.JoinDTO.builder()
                .reviewId(review.getId())
                .createAt(review.getCreated_at())
                .build();
    }

    public static Review toReview(
            ReviewReqDTO.JoinDTO dto, Member member, Store store
    ){
        return Review.builder()
                .content(dto.content())
                .rate(dto.rate())
                .member(member)
                .store(store)
                .build();
    }
}
