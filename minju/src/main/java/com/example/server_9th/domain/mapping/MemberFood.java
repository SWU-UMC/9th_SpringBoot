package com.example.server_9th.domain.mapping;

import com.example.server_9th.domain.Member;
import com.example.server_9th.domain.enums.FoodType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class MemberFood {
    @Id
    @Column(name = "user_id")
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FoodType foodType;

    @OneToOne
    @MapsId // userId가 PK이자 FK임을 명시
    @JoinColumn(name = "user_id")
    private Member member;
}
