package kr.hhplus.be.server.user.domain.model;

import kr.hhplus.be.server.common.ErrorConstants;

import java.time.LocalDateTime;

public class User {
    private final Long id;
    private final String userNm;
    private int balance;
    private LocalDateTime updateDate;

    public User(Long id, String userNm, int balance, LocalDateTime updateDate) {
        this.id = id;
        this.userNm = userNm;
        this.balance = balance;
        this.updateDate = updateDate;
    }

    // 충전
    public void charge(int amount) {
        // 충전하려는 금액이 0보다 커야 한다.
        if (amount < 0) {
            throw new IllegalArgumentException(ErrorConstants.BALANCE_SAVE_ZERO_OR_NEGATIVE.message());
        }
        // 충전하려는 금액은 보유 잔액의 Max인 500,000 보다 작아야 한다.
        if (this.balance + amount > 500000) {
            throw new IllegalArgumentException(ErrorConstants.BALANCE_SAVE_EXCEEDS_TOTAL_LIMIT.message());
        }
        this.balance += amount;
        this.updateDate = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public String getUserNm() { return userNm; }
    public int getBalance() { return balance; }
    public LocalDateTime getUpdateDate() { return updateDate; }
}
