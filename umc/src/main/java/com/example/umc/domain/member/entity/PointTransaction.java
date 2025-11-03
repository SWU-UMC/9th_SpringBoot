package com.example.umc.domain.member.entity;

import com.example.umc.domain.member.enums.TransactionType;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "point_transaction")
public class PointTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // User와의 관계 설정 (회원 ID)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;

    // 포인트 금액 (예: +500, -200)
    @Column(name = "amount", nullable = false)
    private Integer amount;

    // 포인트 거래 날짜
    @Column(name = "transaction_date", nullable = false)
    private LocalDateTime transactionDate;

    // 포인트 거래 종류 (예: 입금, 출금)
    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type", nullable = false)
    private TransactionType transactionType;

    // Getter, Setter 추가
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }
}
