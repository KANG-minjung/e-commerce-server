package kr.hhplus.be.server.coupon.adapter.repository;

import kr.hhplus.be.server.coupon.domain.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponJpaEntityRepository extends JpaRepository<Coupon, Long> {
}
