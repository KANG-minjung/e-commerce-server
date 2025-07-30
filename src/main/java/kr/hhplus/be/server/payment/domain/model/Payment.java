package kr.hhplus.be.server.payment.domain.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class Payment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderId;

    private Long userId;

    private Long couponId; // Nullable

    private int finalAmount;

    private LocalDateTime paidAt;

    protected Payment() {}

    public Payment(Long orderId, Long userId, Long couponId, int finalAmount) {
        this.orderId = orderId;
        this.userId = userId;
        this.couponId = couponId;
        this.finalAmount = finalAmount;
        this.paidAt = LocalDateTime.now();
    }
}
