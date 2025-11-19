package com.example.shop.order.repository;

import com.example.shop.order.Order;

import java.util.List;
import java.util.Optional;


public interface OrderRepository {

    // 주문 ID(PK)로 주문을 조회합니다.
    Optional<Order> findById(Long orderId);

    // 특정 회원 ID로 모든 주문을 조회합니다.
    List<Order> findByMemberId(Long memberId);

    // 주문을 저장합니다.
    void save(Order order);
}