package com.example.shop.product.repository;

import com.example.shop.product.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class JpaProductRepository implements ProductRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<Product> findByProductCode(String productCode) {
        List<Product> result = em.createQuery(
                "SELECT p FROM Product p WHERE p.productCode = :code", Product.class
        ).setParameter("code", productCode).getResultList();

        return result.stream().findFirst();
    }

    @Override
    public List<Product> findAll() {
        return em.createQuery("SELECT p FROM Product p", Product.class)
                .getResultList();
    }

    @Override
    public void save(Product product) {
        // JPA의 영속성 컨텍스트에 저장합니다.
        em.persist(product);
    }

    @Override
    // 비즈니스 규칙에 따라 상품 상태를 직접 업데이트합니다.
    public void updateStatusToDiscontinued(String productCode) {
        // 이 방식은 영속성 컨텍스트를 우회하여 대량 업데이트 시 유용합니다.
        em.createQuery(
                "UPDATE Product p SET p.status = '판매 중지' WHERE p.productCode = :code"
        ).setParameter("code", productCode).executeUpdate();
    }
}