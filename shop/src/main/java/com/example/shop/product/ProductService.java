package com.example.shop.product;

import com.example.shop.product.dto.ProductCreateRequest;
import com.example.shop.product.dto.ProductUpdateRequest;
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
        Product existingProduct = productRepository.findByProductCode(request.getProductCode()); //TODO 구현 없이 선언부만 작성해둠
        if(existingProduct != null) {
            throw new RuntimeException("이미 존재하는 상품입니다: " + request.getProductCode());
        }

        Product product = new Product(
            request.getProductCode(),
            request.getProductName(),
            request.getStock(),
            request.getPrice(),
            request.getStatus()
        );

        productRepository.save(product); //TODO 구현 없이 선언부만 작성해둠

        return product.getId();
    }

    @Transactional
    public List<Product> getAllProducts() {
        return productRepository.findAll(); //TODO 구현 없이 선언부만 작성해둠
    }

    @Transactional
    public Product getProductByCode(String productCode) {
        Product product = productRepository.findByProductCode(productCode);

        if(product == null) {
            throw new RuntimeException("상품을 찾을 수 없습니다");
        }
        return product;
    }

    // 상품 정보 수정
    @Transactional
    public void updateProduct(String productCode, ProductUpdateRequest request) {
        Product product = productRepository.findByProductCode(productCode);

        if(product == null) {
            throw new RuntimeException("상품을 찾을 수 없습니다.");
        }

        product.updateInfo(request.getProductName(), request.getPrice(), request.getStatus());
    }


    @Transactional
    public void deleteProduct(String productCode) {
        Product product = productRepository.findByProductCode(productCode);

        if(product == null) {
            throw new RuntimeException("상품을 찾을 수 없습니다.");
        }

        productRepository.deleteByProductCode(productCode); //TODO 구현 없이 선언부만 작성해둠
    }
}
