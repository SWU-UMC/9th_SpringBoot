package com.umc.umc9th.domain.review.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {

  @Builder
  public record CreateDTO(
      ReviewDTO review
  ) {}

  @Builder
  public record ReviewDTO(
      Integer review_id,
      UserDTO user,
      StoreDTO store,
      BigDecimal rating,
      String content,
      List<ImageDTO> images,
      LocalDateTime created_at
  ) {}

  @Builder
  public record UserDTO(
      Integer user_id,
      String name
  ) {}

  @Builder
  public record StoreDTO(
      Integer store_id,
      String store_name
  ) {}

  @Builder
  public record ImageDTO(
      Integer image_id,
      String image_url
  ) {}
}
