package kr.hhplus.be.server.coupon.domain.model;

import kr.hhplus.be.server.common.BusinessException;
import kr.hhplus.be.server.common.ErrorCode;
import lombok.AccessLevel;
import lombok.Getter;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_coupon")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String couponNm;

    @Enumerated(EnumType.STRING)
    private CouponType couponType;

    @Column(nullable = false)
    private Float discountRate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CouponStatus status;

    private int issueLimit; // 선착순 제한 수량

    private int issuedCount;

    public void issue() {
        if (issuedCount >= issueLimit) {
            throw new BusinessException(ErrorCode.COUPON_OUT_OF_STOCK);
        }
        this.issuedCount++;
    }

    public int calculateDiscount(int orderPrice) {
        if (this.discountRate <= 0 || this.discountRate > 1) {
            throw new BusinessException(ErrorCode.INVALID_COUPON_DISCOUNT);
        }

        if (couponType == CouponType.RATE) {
            return Math.round(orderPrice * (discountRate / 100.0f));
        }
        return 0;
    }

    public void use() {
        if (this.status == CouponStatus.USED) {
            throw new BusinessException(ErrorCode.COUPON_ALREADY_USED);
        }
        this.status = CouponStatus.USED;
    }
}
