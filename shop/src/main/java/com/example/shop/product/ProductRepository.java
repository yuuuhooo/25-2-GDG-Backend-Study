package com.example.shop.product;

import com.example.shop.product.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.sql.Struct;
import java.util.List;

@Repository
public class ProductRepository {

    @PersistenceContext
    private EntityManager em;

    public Product findByProductCode(String productCode) {
        return em.find(Product.class, productCode);
    }

    public void save(Product product) {
        em.persist(product);
    }

    public List<Product> findAll() {
        return em.createQuery("SELECT p FROM Product p", Product.class)
                .getResultList();
    }

    public void deleteByProductCode(String productCode) {
        em.find(Product.class, productCode); //TODO 이거 판매상태 변경으로 수정해야 될 듯

    }
}
