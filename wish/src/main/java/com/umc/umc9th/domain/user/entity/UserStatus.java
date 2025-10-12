package com.umc.umc9th.domain.user.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserStatus {
  ACTIVE,
  INACTIVE;
}