package com.example.shop.product.service;

import com.example.shop.product.entity.Product;
import com.example.shop.product.dto.ProductCreateRequest;
import com.example.shop.product.dto.ProductUpdateRequest;

import java.util.List;

public interface ProductService {

    // 새로운 상품 등록
    Long createProduct(ProductCreateRequest request);

    // 상품 목록 조회
    List<Product> getAllProducts();

    // 개별 상품 정보 상세 조회
    Product getProductByCode(String productCode);

    // 상품 정보 수정
    void updateProduct(String productCode, ProductUpdateRequest request);

    // 상품 판매 중지
    void deleteProduct(String productCode);
}