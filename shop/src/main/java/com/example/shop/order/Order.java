package com.example.shop.order;

import com.example.shop.member.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "orders")
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "total_price")
    private int totalPrice;

    @Column(name = "point_used")
    private int pointUsed;

    @Column(name = "cash_amount")
    private int cashAmount;

    @Column(name = "status", length = 25)
    private String status;


    public Order(Member memberId, LocalDateTime orderDate, int totalPrice, int pointUsed, int cashAmount){
        this.member = memberId;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.pointUsed = pointUsed;
        this.cashAmount = cashAmount;
        this.status = "상품 준비 중";
    }

    public void cancel() {
        // 1. 현재 상태가 취소 가능한 상태인지 검증
        if (this.status.equals("배송 완료")) {
            throw new RuntimeException("이미 배송 완료된 상품은 취소할 수 없습니다.");
        }
        if (this.status.equals("주문 취소")) {
            throw new RuntimeException("이미 취소된 주문입니다.");
        }
        // 2. 상태 변경
        this.status = "주문 취소";
    }
}
