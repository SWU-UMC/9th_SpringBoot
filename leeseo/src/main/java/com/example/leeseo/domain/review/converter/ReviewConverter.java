package com.example.leeseo.domain.review.converter;

import com.example.leeseo.domain.member.entity.Member;
import com.example.leeseo.domain.review.dto.ReviewReqDTO;
import com.example.leeseo.domain.review.dto.ReviewResDTO;
import com.example.leeseo.domain.review.entity.Review;
import com.example.leeseo.domain.review.entity.ReviewPhoto;
import com.example.leeseo.domain.store.entity.Store;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

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

    public static ReviewResDTO.ReviewPreViewListDTO toReviewPreviewListDTO(
            Page<Review> result
    ){
        return ReviewResDTO.ReviewPreViewListDTO.builder()
                .reviewList(result.getContent().stream()
                        .map(ReviewConverter::toReviewPreviewDTO)
                        .toList()
                )
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    public static ReviewResDTO.ReviewPreViewDTO toReviewPreviewDTO(
            Review review
    ){
        return ReviewResDTO.ReviewPreViewDTO.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getRate())
                .body(review.getContent())
                .createdAt(review.getCreated_at())
                .build();
    }

    public static ReviewResDTO.MyReviewListDTO toMyReviewListDTO(
            Page<Review> result
    ){
        return ReviewResDTO.MyReviewListDTO.builder()
                .reviewList(result.getContent().stream()
                        .map(ReviewConverter::toMyReviewDTO).toList())
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    public static ReviewResDTO.MyReviewDTO toMyReviewDTO(
            Review review
    ){
        return ReviewResDTO.MyReviewDTO.builder()
                .rate(review.getRate())
                .content(review.getContent())
                .img_url(review.getReviewPhotoList().stream().map(ReviewPhoto::getPhoto_url).toList())
                .createdAt(review.getCreated_at())
                .build();
    }
}
