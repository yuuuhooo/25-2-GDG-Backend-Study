package com.example.shop.product;

import com.example.shop.product.dto.ProductCreateRequest;
import com.example.shop.product.dto.ProductUpdateRequest;
import com.example.shop.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;


    // 새로운 상품 등록
    @Transactional
    public Long createProduct(ProductCreateRequest request) {
        if(productRepository.findByProductCode(request.getProductCode()).isPresent()) {
            throw new RuntimeException("이미 존재하는 상품입니다: " + request.getProductCode());
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

    // 상품 목록 조회
    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // 개별 상품 정보 상세 조회
    @Transactional(readOnly = true)
    public Product getProductByCode(String productCode) {
        // Optional을 사용하여 조회하고, 없으면 예외 처리
        return productRepository.findByProductCode(productCode)
                .orElseThrow(() -> new RuntimeException("상품을 찾을 수 없습니다: " + productCode));
    }

    // 상품 정보 수정
    @Transactional
    public void updateProduct(String productCode, ProductUpdateRequest request) {
        Product product = productRepository.findByProductCode(productCode)
                .orElseThrow(() -> new RuntimeException("상품을 찾을 수 없습니다."));

        product.updateInfo(request.getProductName(), request.getPrice(), request.getStatus());
    }


    // 💡 상품 판매 중지
    @Transactional
    public void deleteProduct(String productCode) {
        Product product = productRepository.findByProductCode(productCode)
                .orElseThrow(() -> new RuntimeException("상품을 찾을 수 없습니다."));
        product.discontinue();
    }
}