package kr.hhplus.be.server.coupon.domain.model;

import kr.hhplus.be.server.common.BusinessException;
import kr.hhplus.be.server.common.ErrorCode;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserCoupon {
    private final Long id;
    private final Long userId;
    private final Long couponId;
    private String status; // AVAILABLE, USED, EXPIRED
    private final LocalDateTime issuedDate;
    private LocalDateTime usedDate;

    public UserCoupon(Long id, Long userId, Long couponId, String status, LocalDateTime issuedDate, LocalDateTime usedDate) {
        this.id = id;
        this.userId = userId;
        this.couponId = couponId;
        this.status = status;
        this.issuedDate = issuedDate;
        this.usedDate = usedDate;
    }

    public void validateAvailable() {
        if (!"AVAILABLE".equals(this.status)) {
            throw new BusinessException(ErrorCode.COUPON_ALREADY_USED);
        }
    }

    public void markUsed() {
        this.status = "USED";
        this.usedDate = LocalDateTime.now();
    }
}
