package com.umc.umc9th.domain.mission.entity;

import com.umc.umc9th.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_mission")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserMission {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_mission_id")
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "mission_id")
  private Mission mission;

  @Column(length = 20, nullable = false)
  private String status;  // PROGRESS, SUCCESS_REQUESTED, COMPLETED

  @Column(name = "verification_code", length = 10)
  private String verificationCode;

  private LocalDateTime startedAt = LocalDateTime.now();
  private LocalDateTime completedAt;
  private LocalDateTime confirmedAt;
}