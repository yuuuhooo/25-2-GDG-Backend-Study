package com.example.shop.product.controller;

import com.example.shop.product.dto.ProductCreateRequest;
import com.example.shop.product.dto.ProductUpdateRequest;
import com.example.shop.product.entity.Product;
import com.example.shop.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    //Product 구조: productId, productName, price, status(판매 상태)

    @PostMapping // 상품 정보 등록
    public ResponseEntity<Void> createProduct(@RequestBody @Valid ProductCreateRequest request) {
        Long productId = productService.createProduct(request);
        return ResponseEntity.created(URI.create("/products" + productId)).build();
    }

    @GetMapping // 상품 목록 조회
    public ResponseEntity<List<Product>> getAllProduct() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{productCode}") // 개별 상품 정보 상세 조회
    public ResponseEntity<Product> getProduct(@PathVariable String productCode) {
        Product product = productService.getProductByCode(productCode);
        return ResponseEntity.ok(product);
    }

    @PatchMapping("/{productCode}") // 상품 정보 수정
    public ResponseEntity<Void> updateProduct(
            @PathVariable String productCode,
            @RequestBody ProductUpdateRequest request) {
        productService.updateProduct(productCode, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{productCode}") // 잘못 등록한 상품 삭제
    public ResponseEntity<Void> deleteProduct(@PathVariable String productCode) {
        productService.deleteProduct(productCode);
        return ResponseEntity.noContent().build();
    }
}

