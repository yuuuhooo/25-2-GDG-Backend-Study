package com.example.shop.product.dto;

import lombok.Getter;

@Getter
public class ProductUpdateRequest {
    private String productName;
    private Long price;
    private String status;

    public ProductUpdateRequest(String name, Long price, String status) {
        this.productName = name;
        this.price = price;
        this.status = status;
    }
}
