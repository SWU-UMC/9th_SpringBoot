package com.example.umc9th.domain.inquire.entity;

import com.example.umc9th.global.common.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
@Table(name = "inquire_photo")
public class InquirePhoto extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inquire_id", nullable = false)
    private Inquire inquire;

    @NotNull
    @Size(max = 512)
    @Column(name = "image_url", nullable = false, length = 512)
    private String imageUrl;

    public void setInquire(Inquire inquire) { this.inquire = inquire; }

}
