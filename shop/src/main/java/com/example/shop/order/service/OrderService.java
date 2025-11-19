package com.example.shop.order.service;

import com.example.shop.order.dto.OrderCreateRequest;
import com.example.shop.order.entity.Order;

import java.util.List;

// 서비스 계층의 계약(Contract) 역할을 담당합니다.
public interface OrderService {

    // 주문 생성
    Long createOrder(Long memberId, OrderCreateRequest request);

    // 회원별 주문 목록 조회
    List<Order> getOrdersByMemberId(Long memberId);

    // 개별 주문 상세 조회 (소유자 검증 포함)
    Order getOrderByOrderIdAndMemberId(Long orderId, Long memberId);

    // 주문 취소 (소유자 검증 포함)
    void cancelOrder(Long memberId, Long orderId);
}