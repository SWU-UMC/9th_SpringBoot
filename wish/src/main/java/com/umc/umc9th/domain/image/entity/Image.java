package com.umc.umc9th.domain.image.entity;

import com.umc.umc9th.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "image")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Image extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "image_id")
  private Integer id;

  @Column(name = "image_url", length = 500, nullable = false)
  private String imageUrl;

  @Column(length = 20, nullable = false)
  private String target;  // STORE, REVIEW, INQUIRY 등

  @Column(name = "target_id", nullable = false)
  private Integer targetId;
}