package com.example.shop.member;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    public Member findById(Long id) {
        return em.find(Member.class, id); // JPA가 알아서 해당 데이터를 찾아서 멤버 객체로 변환을 해줌
    }

    public List<Member> findAll() {
        return em.createQuery("SELECT m FROM Member m", Member.class) // SELECT m FROM Member m <- 멤버 엔티티를 m이라는 별칭으로 두고, 그 m에 해당하는 모든 데이터를 조회하겠다
                .getResultList(); // Member.class <- 조회를 멤버 타입으로 매핑하겠다는 뜻. 쿼리 결과로 나온 데이터를 JPA가 자동으로 멤버 객체로 변환해줌
    }

    public Member findByLoginId(String loginId) {
        List<Member> result = em.createQuery(
                "SELECT m FROM Member m WHERE m.loginId = :loginId", Member.class // 변수이름 앞에 : 붙이면 파라미터 변수 사용 가능
        ).setParameter("loginId", loginId).getResultList(); // setParameter() 파라미터 바인딩

        return result.isEmpty() ? null : result.get(0); // result.get(0) <- 리스트의 첫번째 회원 반환
    }

    public void save(Member member) {
        em.persist(member);
    }

    public void deleteById(Long id) {
        Member member = em.find(Member.class, id); // 반드시 삭제 전에 해당 엔티티를 영속성 컨텍스트에 등록해야 함
        em.remove(member);
    }
}
