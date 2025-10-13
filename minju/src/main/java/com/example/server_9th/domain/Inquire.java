package com.example.server_9th.domain;

import com.example.server_9th.domain.common.BaseEntity;
import com.example.server_9th.domain.enums.InquireStatus;
import com.example.server_9th.domain.enums.InquireType;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Inquire extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inquired_id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String content;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "inquire_images", joinColumns = @JoinColumn(name = "inquired_id"))
    @Column(name = "url", nullable = false)
    private List<String> imageUrls = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private InquireStatus inquireStatus = InquireStatus.RECEIVED;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InquireType inquireType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Member member;
}
