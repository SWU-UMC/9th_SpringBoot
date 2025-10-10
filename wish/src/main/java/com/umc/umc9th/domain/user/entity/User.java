package com.umc.umc9th.domain.user.entity;

import com.umc.umc9th.domain.address.entity.Address;
import com.umc.umc9th.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "user")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Integer id;

  @Column(length = 10, nullable = false)
  private String name;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String password;

  @Column
  private String phone;

  @Enumerated(EnumType.STRING)
  @Column
  private Gender gender;

  @Column(name = "birth_date")
  private LocalDate birthDate;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "address_id")
  private Address address;

  @Column(length = 200)
  private String detailedAddress;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "current_dong_id")
  private Address currentDong;

  @Builder.Default
  @Column(name = "total_points", nullable = false)
  private Integer totalPoints = 0;

  @Builder.Default
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private UserStatus status = UserStatus.ACTIVE;
}