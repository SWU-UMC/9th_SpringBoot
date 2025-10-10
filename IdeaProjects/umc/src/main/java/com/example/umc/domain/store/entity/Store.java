package com.example.umc.domain.store.entity;

import com.example.umc.domain.region.entity.Region;
import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name="store")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Store {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeId;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name="region_id", nullable=false)
    private Region region;

    @Column(name = "name", length=100, nullable=false)
    private String name;
    @Column(name = "manager_number", length=100)
    private String managerNumber;
    @Column(name = "address", length=255)
    private String address;
    @Lob
    private String description;
}
