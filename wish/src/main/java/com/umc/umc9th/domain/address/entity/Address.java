package com.umc.umc9th.domain.address.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "address")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "address_id")
  private Integer id;

  @Column(nullable = false)
  private String city;

  @Column(nullable = false)
  private String district;

  @Column(nullable = false)
  private String dong;
}
