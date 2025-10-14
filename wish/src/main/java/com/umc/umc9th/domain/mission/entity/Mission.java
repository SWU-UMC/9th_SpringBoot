package com.umc.umc9th.domain.mission.entity;

import com.umc.umc9th.domain.store.entity.Store;
import com.umc.umc9th.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "mission")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mission extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "mission_id")
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "store_id")
  private Store store;

  @Column(name = "mission_description", length = 200, nullable = false)
  private String missionDescription;

  @Column(name = "min_amount", nullable = false)
  private Integer minAmount;

  @Column(name = "reward_points", nullable = false)
  private Integer rewardPoints;

  @Column(name = "deadline", nullable = false)
  private LocalDateTime deadline;

  @Column(name = "is_active", nullable = false)
  private Boolean isActive = true;
}