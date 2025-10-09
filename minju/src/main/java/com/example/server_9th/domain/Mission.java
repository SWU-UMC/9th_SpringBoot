package com.example.server_9th.domain;

import com.example.server_9th.domain.common.BaseEntity;
import com.example.server_9th.domain.mapping.Point;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Mission extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mission_id;

    @Column(nullable = false,length = 100)
    private String name;

    @Column(nullable = false)
    private String content;

    private Integer certificationNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @OneToMany(mappedBy = "mission", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Point> points = new ArrayList<>();

}
