package com.umc.umc9th.domain.store.entity;

import com.umc.umc9th.domain.address.entity.Address;
import com.umc.umc9th.domain.food.entity.FoodCategory;
import com.umc.umc9th.domain.owner.entity.Owner;
import com.umc.umc9th.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "store")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Store extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "store_id")
  private Integer id;

  @Column(name = "store_name", length = 100, nullable = false)
  private String storeName;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id", nullable = false)
  private FoodCategory category;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "address_id")
  private Address address;

  @Column(name = "store_address_detail", length = 200)
  private String storeAddressDetail;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "owner_id")
  private Owner owner;
}