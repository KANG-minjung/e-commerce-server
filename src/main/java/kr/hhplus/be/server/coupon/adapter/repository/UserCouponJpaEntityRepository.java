package kr.hhplus.be.server.coupon.adapter.repository;

import kr.hhplus.be.server.coupon.domain.model.UserCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserCouponJpaEntityRepository extends JpaRepository<UserCoupon, Long> {
    Optional<UserCoupon> findByUser_IdAndCoupon_Id(Long userId, Long couponId);

    @Query("SELECT uc FROM UserCoupon uc WHERE uc.user.id = :userId")
    List<UserCoupon> findByUserId(@Param("userId") Long userId);
}
