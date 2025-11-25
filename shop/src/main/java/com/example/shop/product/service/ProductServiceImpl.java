package com.example.shop.product.service;

import com.example.shop.common.exception.BadRequestException;
import com.example.shop.product.entity.Product;
import com.example.shop.product.dto.ProductCreateRequest;
import com.example.shop.product.dto.ProductUpdateRequest;
import com.example.shop.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor

public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    // 새로운 상품 등록
    @Transactional
    public Long createProduct(ProductCreateRequest request) {
        if(productRepository.findByProductCode(request.getProductCode()).isPresent()) {
            throw new BadRequestException("이미 존재하는 상품입니다: " + request.getProductCode());
        }

        Product product = new Product(
                request.getProductCode(),
                request.getProductName(),
                request.getStock(),
                request.getPrice(),
                request.getStatus()
        );

        productRepository.save(product);

        return product.getId();
    }

    @Override
    // 상품 목록 조회
    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    // 개별 상품 정보 상세 조회
    @Transactional(readOnly = true)
    public Product getProductByCode(String productCode) {
        return productRepository.findByProductCode(productCode)
                .orElseThrow(() -> new RuntimeException("상품을 찾을 수 없습니다: " + productCode));
    }

    @Override
    // 상품 정보 수정
    @Transactional
    public void updateProduct(String productCode, ProductUpdateRequest request) {
        Product product = productRepository.findByProductCode(productCode)
                .orElseThrow(() -> new RuntimeException("상품을 찾을 수 없습니다."));

        product.updateInfo(request.getProductName(), request.getPrice(), request.getStatus());
    }

    @Override
    @Transactional
    public void deleteProduct(String productCode) {
        Product product = productRepository.findByProductCode(productCode)
                .orElseThrow(() -> new RuntimeException("상품을 찾을 수 없습니다."));

        product.discontinue();
    }
}