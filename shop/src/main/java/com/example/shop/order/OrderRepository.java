package com.example.shop.order;

import com.example.shop.member.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepository {

    @PersistenceContext
    private EntityManager em;

    public Order findByOrderId(Long orderId) {
        return em.find(Order.class, orderId);
    }
    
    public List<Order> findByMemberId(Long memberId) {
        List<Order> result = em.createQuery(
                "SELECT o FROM Order o WHERE o.memberId = :memberId", Order.class
        ).setParameter("memberId", memberId).getResultList();

        return result;
    }

    public void save(Order order) {
        em.persist(order);
    }
}
