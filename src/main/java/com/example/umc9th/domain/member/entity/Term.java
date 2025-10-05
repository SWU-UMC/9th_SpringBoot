package com.example.umc9th.domain.member.entity;

import com.example.umc9th.domain.member.entity.mapping.MemberTerm;
import com.example.umc9th.domain.member.enums.TermName;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "term")
public class Term {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private TermName name;

    // 연관 관계
    @OneToMany(mappedBy = "term")
    private List<MemberTerm> memberTermList = new ArrayList<>();
}
