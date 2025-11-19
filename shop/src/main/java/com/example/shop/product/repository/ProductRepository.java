package com.example.shop.product.repository;

import com.example.shop.product.Product;

import java.util.List;
import java.util.Optional;


public interface ProductRepository {

    // 비즈니스 코드(productCode)로 상품을 조회합니다.
    Optional<Product> findByProductCode(String productCode);

    // 모든 상품을 조회합니다.
    List<Product> findAll();

    // 상품을 저장/수정합니다.
    void save(Product product);

    // 상품의 판매 상태를 '판매 중지'로 변경
    void updateStatusToDiscontinued(String productCode);
}