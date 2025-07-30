package kr.hhplus.be.server.coupon.domain.model;

import kr.hhplus.be.server.common.BusinessException;
import kr.hhplus.be.server.common.ErrorCode;
import lombok.Getter;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Getter
public class Coupon {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // 예: "10% 할인"

    @Enumerated(EnumType.STRING)
    private CouponType type; // RATE, FIXED

    private double discountRate;

    private int issueLimit; // 선착순 제한 수량

    private int issuedCount;

    private LocalDateTime createdAt;

    protected Coupon() {}

    public Coupon(String name, CouponType type, double rate, int limit) {
        this.name = name;
        this.type = type;
        this.discountRate = rate;
        this.issueLimit = limit;
        this.issuedCount = 0;
        this.createdAt = LocalDateTime.now();
    }

    public void issue() {
        if (issuedCount >= issueLimit) {
            throw new BusinessException(ErrorCode.COUPON_OUT_OF_STOCK);
        }
        this.issuedCount++;
    }
}
