package com.umc.umc9th.domain.review.entity;

import com.umc.umc9th.domain.store.entity.Store;
import com.umc.umc9th.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "review")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "review_id")
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "store_id")
  private Store store;

  @Column(precision = 2, scale = 1, nullable = false)
  private BigDecimal rating;

  @Lob
  @Column(nullable = false)
  private String content;
}