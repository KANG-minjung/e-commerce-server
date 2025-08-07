package kr.hhplus.be.server.user.domain.model;

import kr.hhplus.be.server.common.BusinessException;
import kr.hhplus.be.server.common.ErrorCode;
import lombok.Getter;
import jakarta.persistence.*;

import java.lang.reflect.Field;
import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_nm", nullable = false)
    private String userNm;

    @Column(nullable = false)
    private int balance;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    protected User() {
    }

    public User(String name, int balance) {
        if (name == null || name.isBlank()) {
            throw new BusinessException(ErrorCode.USER_INVALID);
        }
        if (balance < 0) {
            throw new BusinessException(ErrorCode.INVALID_USER_BALANCE);
        }

        this.userNm = name;
        this.balance = balance;
        this.updateDate = LocalDateTime.now();
    }

    //TestOnly
    public User(Long id, String name, int balance) {
        this.userNm = name;
        this.balance = balance;
        this.updateDate = LocalDateTime.now();

        // 리플렉션으로 id 설정하는 대신 테스트 편의성 위해 허용
        try {
            Field field = User.class.getDeclaredField("id");
            field.setAccessible(true);
            field.set(this, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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

    public void decreaseBalance(int amount) {
        if (amount <= 0) {
            throw new BusinessException(ErrorCode.INVALID_AMOUNT);
        }

        if (this.balance < amount) {
            throw new BusinessException(ErrorCode.BALANCE_INSUFFICIENT);
        }

        this.balance -= amount;
        this.updateDate = LocalDateTime.now();
    }
}
