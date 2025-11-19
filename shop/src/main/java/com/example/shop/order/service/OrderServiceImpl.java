package com.example.shop.order.service;

import com.example.shop.member.entity.Member;
import com.example.shop.member.repository.MemberRepository;
import com.example.shop.order.dto.OrderCreateRequest;
import com.example.shop.order.entity.Order;
import com.example.shop.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor

public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional // 데이터 변경이 있으므로 @Transactional 필수
    public Long createOrder(Long memberId, OrderCreateRequest request) {

        LocalDateTime orderDate = LocalDateTime.now();

        // 2. Member 엔티티 조회: JpaMemberRepository의 반환 타입에 맞춰 null 체크
        Member member = memberRepository.findById(memberId);

        if (member == null) {
            throw new RuntimeException("회원을 찾을 수 없습니다.");
        }

        // Order 엔티티 생성자 호출 및 데이터 전달
        Order order = new Order(
                member,
                orderDate,
                request.getTotalPrice(),
                request.getPointUsed(),
                request.getCashAmount()
        );

        orderRepository.save(order);
        return order.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> getOrdersByMemberId(Long memberId) {
        return orderRepository.findByMemberId(memberId);
    }

    @Override
    @Transactional
    public void cancelOrder(Long memberId, Long orderId) {
        // 1. 주문 조회
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("주문번호가 올바르지 않습니다."));

        if (!order.getId().equals(memberId)) {
            throw new RuntimeException("유효하지 않은 요청입니다. (주문자와 요청자가 불일치)");
        }

        // 3. 엔티티에 취소 로직 위임 (상태 변경 및 비즈니스 규칙)
        order.cancel();
    }

    @Override
    @Transactional(readOnly = true)
    public Order getOrderByOrderIdAndMemberId(Long orderId, Long memberId) {
        // 1. 주문 조회
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("주문번호가 올바르지 않습니다."));

        // 2. 주문 소유자 검증
        if (!order.getId().equals(memberId)) {
            throw new RuntimeException("접근 권한이 없습니다. (주문자와 요청자가 불일치)");
        }

        return order;
    }
}