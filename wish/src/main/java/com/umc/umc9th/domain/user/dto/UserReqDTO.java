package com.umc.umc9th.domain.user.dto;

import java.time.LocalDate;
import java.util.List;

public class UserReqDTO {

  public record JoinDTO(
      String name,
      String email,
      String password,
      String gender,
      LocalDate birth_date,
      AddressDTO address,
      String detailed_address,
      List<Integer> preferred_categories
  ) {}

  public record AddressDTO(
      String city,
      String district,
      String dong
  ) {}
}
