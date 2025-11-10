package com.example.leeseo.domain.store.entity;

import com.example.leeseo.domain.mission.entity.Mission;
import com.example.leeseo.domain.review.entity.Review;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "store")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "manager_number", nullable = false)
    private long manager_number;

    @Column(name = "detail_address", nullable = false)
    private String detail_address;

    // 연관 관계
    @OneToMany(mappedBy = "store")
    private List<Mission> missions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

    @OneToMany(mappedBy = "store")
    private List<Review> reviewList = new ArrayList<>();
}
