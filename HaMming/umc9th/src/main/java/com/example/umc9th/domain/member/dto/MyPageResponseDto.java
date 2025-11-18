package com.example.umc9th.domain.member.dto;

import com.example.umc9th.domain.review.entity.Review;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
/*여러 데이터를 한번에 내려주므로 DTO로 묶어주기*/
@Getter
@Builder
public class MyPageResponseDto {
    private String nickname;
    private String email;
    private String phoneNumber;
    private Integer totalPoints;
    private List<Review> writtenReviews;
}
