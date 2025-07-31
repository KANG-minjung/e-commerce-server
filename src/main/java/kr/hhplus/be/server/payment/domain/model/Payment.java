package kr.hhplus.be.server.payment.domain.model;

import jakarta.persistence.*;
import kr.hhplus.be.server.common.BusinessException;
import kr.hhplus.be.server.common.ErrorCode;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_payment")
@Getter
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_id", nullable = false)
    private Long orderId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "coupon_id")
    private Long couponId;

    @Column(name = "pay_amount", nullable = false)
    private int payAmount;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    protected Payment() {
    }

    public Payment(Long orderId, Long userId, Long couponId, int payAmount) {
        if (orderId == null || orderId <= 0) {
            throw new BusinessException(ErrorCode.ORDER_INVALID);
        }
        if (userId == null || userId <= 0) {
            throw new BusinessException(ErrorCode.INVALID_USER_ID);
        }
        if (payAmount < 0) {
            throw new BusinessException(ErrorCode.INVALID_PAY_AMOUNT);
        }

        this.orderId = orderId;
        this.userId = userId;
        this.couponId = couponId;
        this.payAmount = payAmount;
        this.updateDate = LocalDateTime.now();
    }
}