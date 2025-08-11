package kr.hhplus.be.server.coupon.adapter.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import kr.hhplus.be.server.coupon.domain.model.Coupon;
import kr.hhplus.be.server.coupon.domain.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CouponRepositoryImpl implements CouponRepository {

    private final CouponJpaEntityRepository jpa;

    private final EntityManager em;

    @Override
    public Optional<Coupon> findById(Long id) {
        return jpa.findById(id);
    }

    @Override
    public Coupon save(Coupon coupon) {
        return jpa.save(coupon);
    }

    @Override
    public List<Coupon> findAll() {
        return jpa.findAll();
    }

    @Override
    public Coupon findByIdForUpdate(Long couponId){
        return em.createQuery("""
                SELECT c FROM Coupon c
                WHERE c.id = :id
                """, Coupon.class)
                .setParameter("id", couponId)
                .setLockMode(LockModeType.PESSIMISTIC_WRITE) // FOR UPDATE
                .getSingleResult();
    }
}
