package com.example.shop.order.repository;

import com.example.shop.order.Order;
import com.example.shop.order.repository.OrderRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaOrderRepository implements OrderRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<Order> findById(Long orderId) {
        return Optional.ofNullable(em.find(Order.class, orderId));
    }

    @Override
    public List<Order> findByMemberId(Long memberId) {
        List<Order> result = em.createQuery(
                "SELECT o FROM Order o WHERE o.id = :memberId", Order.class
        ).setParameter("memberId", memberId).getResultList();

        return result;
    }

    @Override
    public void save(Order order) {
        em.persist(order);
    }
}