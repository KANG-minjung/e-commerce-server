package kr.hhplus.be.server.coupon.domain.model;

import kr.hhplus.be.server.common.BusinessException;
import kr.hhplus.be.server.common.ErrorCode;
import lombok.Getter;

@Getter
public class Coupon {
    private final Long id;
    private final String name;
    private final String type; // RATE, FIXED
    private final double discountRate;
    private final int maxIssueCount;
    private int currentIssueCount;

    public Coupon(Long id, String name, String type, double discountRate, int maxIssueCount, int currentIssueCount) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.discountRate = discountRate;
        this.maxIssueCount = maxIssueCount;
        this.currentIssueCount = currentIssueCount;
    }

    public void validateIssueable() {
        if (currentIssueCount >= maxIssueCount) {
            throw new BusinessException(ErrorCode.COUPON_OUT_OF_STOCK);
        }
    }

    public void increaseIssuedCount() {
        this.currentIssueCount++;
    }
}
