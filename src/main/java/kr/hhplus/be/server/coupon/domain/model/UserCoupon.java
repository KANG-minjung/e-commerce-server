package kr.hhplus.be.server.coupon.domain.model;

import kr.hhplus.be.server.common.BusinessException;
import kr.hhplus.be.server.common.ErrorCode;
import lombok.Getter;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Getter
public class UserCoupon {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long couponId;

    @Enumerated(EnumType.STRING)
    private CouponStatus status;

    private LocalDateTime issuedAt;

    private LocalDateTime usedAt;

    protected UserCoupon() {}

    public UserCoupon(Long userId, Long couponId) {
        this.userId = userId;
        this.couponId = couponId;
        this.status = CouponStatus.AVAILABLE;
        this.issuedAt = LocalDateTime.now();
    }

    public void markUsed() {
        if (this.status != CouponStatus.AVAILABLE) {
            throw new BusinessException(ErrorCode.COUPON_EXPIRED);
        }
        this.status = CouponStatus.USED;
        this.usedAt = LocalDateTime.now();
    }

    public void validateAvailable() {
        if (this.status != CouponStatus.AVAILABLE) {
            throw new BusinessException(ErrorCode.COUPON_EXPIRED);
        }
    }
}