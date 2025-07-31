package kr.hhplus.be.server.coupon.domain.model;

import kr.hhplus.be.server.common.BusinessException;
import kr.hhplus.be.server.common.ErrorCode;
import kr.hhplus.be.server.user.domain.model.User;
import lombok.AccessLevel;
import lombok.Getter;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_user_coupon")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserCoupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private CouponStatus status;

    private LocalDateTime issuedDate;

    private LocalDateTime usedDate;

    public UserCoupon(User user, Coupon coupon) {
        this.user = user;
        this.coupon = coupon;
        this.status = CouponStatus.UNUSED;
        this.issuedDate = LocalDateTime.now();
    }

    public void use() {
        if (this.status == CouponStatus.USED) {
            throw new BusinessException(ErrorCode.COUPON_ALREADY_ISSUED);
        }
        this.status = CouponStatus.USED;
        this.usedDate = LocalDateTime.now();
    }

    public int calculateDiscount(int price) {
        return coupon.calculateDiscount(price);
    }

    public void markUsed() {
        if (this.status != CouponStatus.AVAILABLE) {
            throw new BusinessException(ErrorCode.COUPON_EXPIRED);
        }
        this.status = CouponStatus.USED;
        this.usedDate = LocalDateTime.now();
    }

    public void validateAvailable() {
        if (this.status != CouponStatus.AVAILABLE) {
            throw new BusinessException(ErrorCode.COUPON_EXPIRED);
        }
    }

    public Long getUserId() {
        return this.user != null ? this.user.getId() : null;
    }

    public Long getCouponId() {
        return this.coupon != null ? this.coupon.getId() : null;
    }
}