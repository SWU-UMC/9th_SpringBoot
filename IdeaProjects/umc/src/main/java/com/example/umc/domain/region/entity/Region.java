package com.example.umc.domain.region.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="region")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "region_id")
    private Long regionId;

    @Column(name = "city", length = 50, nullable = false)
    private String city;

    @Column(name = "district", length = 50)
    private String district;

    @Column(name = "neighborhood", length = 50)
    private String neighborhood;
}
