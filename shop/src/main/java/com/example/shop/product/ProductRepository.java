package com.example.shop.product;

import com.example.shop.product.dto.ProductCreateRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository {
    public Product findByProductId(ProductCreateRequest request) {
    }

    public void save(Product product) {
    }

    public List<Product> findAll() {
    }

    public Product findById(Long id) {
    }

    public void deleteById(Long id) {
    }
}
