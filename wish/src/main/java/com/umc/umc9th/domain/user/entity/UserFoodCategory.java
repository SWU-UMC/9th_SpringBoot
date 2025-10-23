package com.umc.umc9th.domain.user.entity;

import com.umc.umc9th.domain.food.entity.FoodCategory;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_food_category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserFoodCategory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id", nullable = false)
  private FoodCategory category;
}