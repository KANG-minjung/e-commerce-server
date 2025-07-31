package kr.hhplus.be.server.coupon.domain.repository;

import jakarta.persistence.LockModeType;
import kr.hhplus.be.server.coupon.domain.model.UserCoupon;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserCouponRepository {
    Optional<UserCoupon> findByUser_IdAndCoupon_Id(Long userId, Long couponId);
    List<UserCoupon> findByUserId(Long userId);
    UserCoupon save(UserCoupon userCoupon);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT c FROM Coupon c WHERE c.id = :id")
    Optional<UserCoupon> findByIdForUpdate(@Param("id") Long id);
}
