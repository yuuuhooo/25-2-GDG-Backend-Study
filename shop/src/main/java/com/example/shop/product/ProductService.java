package com.example.shop.product;

import com.example.shop.product.dto.ProductCreateRequest;
import com.example.shop.product.dto.ProductUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;


    // 새로운 상품 등록
    public Long createProduct(ProductCreateRequest request) {
        Product existingProduct = productRepository.findByProductId(request);
        if(existingProduct != null) {
            throw new RuntimeException("이미 존재하는 상품입니다: " + request.getProductId());
        }

        Product product = new Product(
            request.getProductId(),
            request.getProductName(),
            request.getPrice(),
            request.getStatus()
        );

        productRepository.save(product);

        return product.getId();
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        Product product = productRepository.findById(id);

        if(product == null) {
            throw new RuntimeException("상품을 찾을 수 없습니다");
        }
        return product;
    }

    // 상품 정보 수정
    public void updateProduct(Long id, ProductUpdateRequest request) {
        Product product = productRepository.findById(id);

        if(product == null) {
            throw new RuntimeException("상품을 찾을 수 없습니다.");
        }

        product.updateInfo(request.getProductName(), request.getPrice(), request.getStatus);
    }


    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id);

        if(product == null) {
            throw new RuntimeException("상품을 찾을 수 없습니다.");
        }

        productRepository.deleteById(id);
    }
}
