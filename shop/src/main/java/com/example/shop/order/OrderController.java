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
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final ProductService productService;

    //Order 구조: orderId, memberId(주문자), status(주문 상태; 주문 완료, 주문 취소, 배송 중 등)

    @PostMapping
    public ResponseEntity<Void> createOrder(@RequestBody OrderCreateRequest request) {
        Long orderId = orderService.createOrder(request);
        return ResponseEntity.created(URI.create("/orders" + orderId)).build();
    }

    @GetMapping("/{memberId}") // 주문 목록 조회
    // 실제 쇼핑몰은 "/my"로 URI 작성 후 보안을 위해 로그인 인증 토큰에서 id를 추출하여 서비스 계층에 함께 전달
    public ResponseEntity<List<Order>> getAllOrder(@PathVariable Long memberId) {
        List<Order> orders = orderService.getOrdersByMemberId(memberId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{memberId}/{orderId}") // 개별 주문 정보 상세 조회
    // 타인의 주문을 조회할 수 없게 해야
    public ResponseEntity<Order> getOrder(@PathVariable Long orderId) {
        Order order = orderService.getOrderByOrderId(orderId);
        return ResponseEntity.ok(order);
    }

    @PatchMapping("/{memberId}/{orderId}") // 주문 취소
    public ResponseEntity<Void> cancelOrder(@PathVariable Long orderId, Long memberId) {
        orderService.cancelOrder(orderId, memberId);
        return ResponseEntity.ok().build();
    }



}
