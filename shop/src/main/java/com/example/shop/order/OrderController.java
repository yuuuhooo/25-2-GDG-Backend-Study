package com.example.shop.order;


import com.example.shop.order.dto.OrderCreateRequest;
import com.example.shop.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members/{memberId}/orders")
public class OrderController {
    private final OrderService orderService;

    //Order 구조: orderId, memberId(주문자), status(주문 상태; 주문 완료, 주문 취소, 배송 중 등)

    @PostMapping
    public ResponseEntity<Void> createOrder(@PathVariable Long memberId, @RequestBody OrderCreateRequest request) {
        Long orderId = orderService.createOrder(memberId, request);
        return ResponseEntity.created(URI.create("/members/" + memberId + "/orders/" + orderId)).build();
    }

    @GetMapping // 주문 목록 조회
    // 실제 쇼핑몰은 "/my"로 URI 작성 후 보안을 위해 로그인 인증 토큰에서 id를 추출하여 서비스 계층에 함께 전달
    public ResponseEntity<List<Order>> getAllOrder(@PathVariable Long memberId) {
        List<Order> orders = orderService.getOrdersByMemberId(memberId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{orderId}") // 개별 주문 정보 상세 조회
    // 타인의 주문을 조회할 수 없게 해야
    public ResponseEntity<Order> getOrder(@PathVariable Long memberId, @PathVariable Long orderId) {
        // 서비스에서 소유자 검증을 수행합니다.
        Order order = orderService.getOrderByOrderIdAndMemberId(orderId, memberId);
        return ResponseEntity.ok(order);
    }

    @PatchMapping("/{orderId}") // 주문 취소
    public ResponseEntity<Void> cancelOrder(@PathVariable Long memberId, @PathVariable Long orderId) {
        orderService.cancelOrder(memberId, orderId);
        return ResponseEntity.ok().build();
    }


}
