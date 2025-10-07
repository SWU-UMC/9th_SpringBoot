package com.example.umc9th.domain.inquire.entity;

import com.example.umc9th.domain.inquire.entity.enums.InquireStatus;
import com.example.umc9th.domain.inquire.entity.enums.InquireType;
import com.example.umc9th.domain.user.entity.User;
import com.example.umc9th.global.common.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Inquire extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotBlank
    @Size(max = 2000)
    @Column(name = "content", nullable = false, length = 2000)
    private String content;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 30)
    private InquireType type;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 30)
    private InquireStatus status = InquireStatus.RECEIVED;

    @OneToMany(mappedBy = "inquire", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InquirePhoto> photos = new ArrayList<>();

    @OneToMany(mappedBy = "inquire", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InquireReply> replies = new ArrayList<>();

    public void addPhoto(InquirePhoto p) { photos.add(p); p.setInquire(this); }
    public void removePhoto(InquirePhoto p) { photos.remove(p); p.setInquire(null); }

    public void addReply(InquireReply r) { replies.add(r); r.setInquire(this); }
    public void removeReply(InquireReply r) { replies.remove(r); r.setInquire(null); }

}
