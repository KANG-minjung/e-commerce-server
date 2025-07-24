package kr.hhplus.be.server.user.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import kr.hhplus.be.server.common.BusinessException;
import kr.hhplus.be.server.common.ErrorCode;
import lombok.Getter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Entity
@Getter
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userNm;
    private int balance;
    private LocalDateTime updateDate;

    protected User() {}

    public User(String userNm) {
        if (userNm == null || userNm.trim().isEmpty()) {
            throw new BusinessException(ErrorCode.USER_INVALID);
        }
        this.userNm = userNm;
        this.balance = 0;
        this.updateDate = LocalDateTime.now();
    }

    // 충전
    public void charge(int amount) {
        // 충전하려는 금액이 0보다 커야 한다.
        if (amount <= 0) {
            throw new BusinessException(ErrorCode.INVALID_CHARGE_AMOUNT);
        }

        // 충전하려는 금액은 보유 잔액의 Max인 500,000 보다 작아야 한다.
        if (this.balance + amount > 500000) {
            throw new BusinessException(ErrorCode.BALANCE_SAVE_EXCEEDS_TOTAL_LIMIT);
        }

        this.balance += amount;
        this.updateDate = LocalDateTime.now();
    }

    public void deduct(int amount) {
        if (balance < amount) {
            throw new BusinessException(ErrorCode.BALANCE_INSUFFICIENT);
        }
        this.balance -= amount;
        this.updateDate = LocalDateTime.now();
    }
}
