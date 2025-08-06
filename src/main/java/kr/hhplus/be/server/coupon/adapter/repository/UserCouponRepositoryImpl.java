package kr.hhplus.be.server.coupon.adapter.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import kr.hhplus.be.server.coupon.domain.model.UserCoupon;
import kr.hhplus.be.server.coupon.domain.repository.UserCouponRepository;
import kr.hhplus.be.server.item.domain.model.Item;
import kr.hhplus.be.server.item.domain.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserCouponRepositoryImpl implements UserCouponRepository {

    private final UserCouponJpaEntityRepository jpa;
    private final EntityManager entityManager;

    @Override
    public Optional<UserCoupon> findByUser_IdAndCoupon_Id(Long userId, Long couponId) {
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

    @Override
    public Optional<UserCoupon> findByIdForUpdate(Long id) {
        return entityManager.createQuery("""
                    SELECT c FROM UserCoupon c
                    WHERE c.id = :id
                    """, UserCoupon.class)
                .setParameter("id", id)
                .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                .getResultList().stream().findFirst();
    }
}


