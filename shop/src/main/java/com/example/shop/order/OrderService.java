package com.example.shop.order;

import com.example.shop.member.Member;
import com.example.shop.member.repository.MemberRepository;
import com.example.shop.order.dto.OrderCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository; // 💡 Member 엔티티를 조회하기 위해 추가


    // memberId를 Service 계층에서 받아야 합니다. (인증/인가 미구현 가정)
    @Transactional
    public Long createOrder(Long memberId, OrderCreateRequest request) {

        // 1. 주문 시점 (결제 완료 시점)의 시간 생성
        LocalDateTime orderDate = LocalDateTime.now();

        // 2. Member 엔티티 조회 (Order 생성자에 Member 객체가 필요하므로)
        // Spring Data JPA의 findById를 사용하며, Optional 처리 필요.
        Member member = memberRepository.findById(memberId);

        if(member == null) {
            throw new RuntimeException("회원을 찾을 수 없습니다.");
        }

        // 3. Order 엔티티 생성자 호출 및 데이터 전달
        Order order = new Order(
                member, // 조회한 Member 엔티티 객체 전달
                orderDate, // 생성된 주문 시간 전달
                request.getTotalPrice(),
                request.getPointUsed(),
                request.getCashAmount()
        );

        // 4. 주문 엔티티 저장 (PK는 DB에서 자동 생성됨)
        orderRepository.save(order);
        return order.getId(); // 실제 PK인 id를 반환하도록 엔티티 설계 변경 필요
    }

    @Transactional
    public List<Order> getOrdersByMemberId(Long memberId) {
        List<Order> orders = orderRepository.findByMemberId(memberId); //TODO 구현 없이 선언부만 작성해둠
        return orders;
    }

    @Transactional
    public void cancelOrder(Long memberId, Long orderId) {
        Order order = orderRepository.findByOrderId(orderId);
        if(!order.getMember().getId().equals(memberId)) {
            throw new RuntimeException("유효하지 않은 요청입니다. (주문자와 요청자가 불일치)");
        }

        order.cancel();
    }

    @Transactional
    public Order getOrderByOrderIdAndMemberId(Long orderId, Long memberId) {
        Order order = orderRepository.findByOrderId(orderId);
        if(order == null) {
            throw new RuntimeException("주문번호가 올바르지 않습니다.");
        }

        // 주문 소유자 검증
        if (!order.getMember().getId().equals(memberId)) {
            throw new RuntimeException("접근 권한이 없습니다. (주문자와 요청자가 불일치)");
        }

        return order;
    }
}
