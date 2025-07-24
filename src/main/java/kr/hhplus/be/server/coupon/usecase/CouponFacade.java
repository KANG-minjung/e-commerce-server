package kr.hhplus.be.server.coupon.usecase;

import kr.hhplus.be.server.coupon.domain.model.Coupon;
import kr.hhplus.be.server.coupon.usecase.dto.CouponResponse;
import kr.hhplus.be.server.coupon.usecase.dto.IssueCouponRequest;
import kr.hhplus.be.server.coupon.usecase.dto.IssuedCouponResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CouponFacade {
    private final CouponCommandUseCase command;
    private final CouponQueryUseCase query;

    public CouponFacade(CouponCommandUseCase command, CouponQueryUseCase query) {
        this.command = command;
        this.query = query;
    }

    public void issue(IssueCouponRequest request) {
        command.issue(request);
    }

    public List<IssuedCouponResponse> getUserCoupons(Long userId) {
        return query.getUserCoupons(userId).stream()
                .map(c -> new IssuedCouponResponse(c.getCouponId(), c.getStatus(), c.getIssuedDate(), c.getUsedDate()))
                .toList();
    }

    public CouponResponse getCoupon(Long couponId) {
        Coupon c = query.getCoupon(couponId);
        return new CouponResponse(c.getId(), c.getName(), c.getType(), c.getDiscountRate(), c.getMaxIssueCount(), c.getCurrentIssueCount());
    }

    public void useCoupon(Long userId, Long couponId) {
        command.markUsed(userId, couponId);
    }
}
