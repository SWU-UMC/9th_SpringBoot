package com.example.server_9th.domain;

import com.example.server_9th.domain.common.BaseEntity;
import com.example.server_9th.domain.enums.Category;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Store extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long store_id;

    @Column(nullable = false)
    private String storeName;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String inform;

    private LocalDateTime operating_hour;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Mission> missions = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;
}
