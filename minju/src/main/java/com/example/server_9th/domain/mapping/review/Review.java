package com.example.server_9th.domain.mapping.review;

import com.example.server_9th.domain.Reply;
import com.example.server_9th.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Review extends BaseEntity {
    @EmbeddedId
    private ReviewId id;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "review_images", joinColumns = {
            //복합키로 인한 조인컬럼 설정
            @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            @JoinColumn(name = "store_id", referencedColumnName = "store_id")
    })
    @Column(name = "url", nullable = false)
    private List<String> imageUrls = new ArrayList<>();

    @NotNull
    @DecimalMin("0.0")
    @DecimalMax("5.0")
    private Double rating;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reply_id")
    private Reply reply;
}
