package com.umc.umc9th.domain.review.dto;

import java.math.BigDecimal;
import java.util.List;

public class ReviewReqDTO {

  public record CreateDTO(
      Integer store_id,
      BigDecimal rating,
      String content,
      List<String> image_urls
  ){}
}
