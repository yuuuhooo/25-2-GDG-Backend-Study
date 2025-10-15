package com.example.shop.order;

import com.example.shop.order.dto.OrderCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public Long createOrder(OrderCreateRequest request) {
        Order existingOrder = orderRepository.findByOrderId(request.getOrderId());
        if(existingOrder != null) {
            throw new RuntimeException("이미 존재하는 상품입니다.");
        }

        Order order = new Order(
          request.getOrderId(),
          request.getMemberId(),
          request.getStatus()
        );

        orderRepository.save(order); //TODO 구현 없이 선언부만 작성해둠

        return order.getOrderId();
    }

    public List<Order> getOrdersByMemberId(Long memberId) {
        List<Order> orders = orderRepository.findByMemberId(memberId); //TODO 구현 없이 선언부만 작성해둠
        return orders;
    }


    public Order getOrderByOrderId(Long orderId) {
        Order order = orderRepository.findByOrderId(orderId); //TODO 구현 없이 선언부만 작성해둠
        if(order == null) {
            throw new RuntimeException("주문번호가 올바르지 않습니다.");
        }

        return order;
    }

    public void cancelOrder(Long orderId, Long memberId) {
        Order order = orderRepository.findByOrderId(orderId);
        if(order.getMemberId() != memberId) {
            throw new RuntimeException("유효하지 않은 요청입니다. (주문자와 요청자가 불일치)");
        }

        order.cancel();
    }

}
