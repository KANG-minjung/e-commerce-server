package kr.hhplus.be.server.coupon.adapter.repository;

import kr.hhplus.be.server.coupon.domain.model.UserCoupon;
import kr.hhplus.be.server.coupon.domain.repository.UserCouponRepository;
import kr.hhplus.be.server.item.domain.model.Item;
import kr.hhplus.be.server.item.domain.repository.ItemRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserCouponRepositoryImpl implements UserCouponRepository {

    private final UserCouponJpaEntityRepository jpa;

    public UserCouponRepositoryImpl(UserCouponJpaEntityRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public Optional<UserCoupon> findByUserIdAndCouponId(Long userId, Long couponId) {
        return jpa.findByUserIdAndCouponId(userId, couponId);
    }

    @Override
    public List<UserCoupon> findByUserId(Long userId) {
        return jpa.findByUserId(userId);
    }

    @Override
    public UserCoupon save(UserCoupon userCoupon) {
        return jpa.save(userCoupon);
    }
}


