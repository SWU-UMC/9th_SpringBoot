package com.example.leeseo.domain.member.entity;

import com.example.leeseo.domain.member.entity.mapping.MemberFood;
import com.example.leeseo.domain.member.enums.FoodName;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "food")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private FoodName name;

    // 연관 관계
    @OneToMany(mappedBy = "food")
    private List<MemberFood> memberFoodList = new ArrayList<>();

}
