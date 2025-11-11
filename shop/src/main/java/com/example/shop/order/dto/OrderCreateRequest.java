package com.example.shop.order.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class OrderCreateRequest {
    private LocalDateTime orderDate;
    private int totalPrice;
    private int pointUsed;
    private int cashAmount;
    private String status;

    public OrderCreateRequest(LocalDateTime orderDate, int totalPrice, int pointUsed, int cashAmount, String status) {
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.pointUsed = pointUsed;
        this.cashAmount = cashAmount;
        this.status = status;
    }

}
