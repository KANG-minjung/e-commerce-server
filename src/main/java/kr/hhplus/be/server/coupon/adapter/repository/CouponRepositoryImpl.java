package kr.hhplus.be.server.coupon.adapter.repository;

import kr.hhplus.be.server.coupon.domain.model.Coupon;
import kr.hhplus.be.server.coupon.domain.repository.CouponRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CouponRepositoryImpl implements CouponRepository {

    private final CouponJpaEntityRepository jpa;

    public CouponRepositoryImpl(CouponJpaEntityRepository jpa) {
        this.jpa = jpa;
    }

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
}
