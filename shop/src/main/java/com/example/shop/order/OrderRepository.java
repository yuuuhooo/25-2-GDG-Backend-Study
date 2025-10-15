package com.example.shop.order;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepository {
    public Order findByOrderId(Long orderId) {
        
    }
    
    public List<Order> findByMemberId(Long memberId) {
    }

    public void save(Order order) {
    }
}
