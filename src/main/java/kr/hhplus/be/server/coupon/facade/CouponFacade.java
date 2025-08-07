package kr.hhplus.be.server.coupon.facade;

import kr.hhplus.be.server.coupon.domain.model.Coupon;
import kr.hhplus.be.server.coupon.domain.model.UserCoupon;
import kr.hhplus.be.server.coupon.usecase.command.ApplyCouponUseCase;
import kr.hhplus.be.server.coupon.usecase.command.IssueCouponUseCase;
import kr.hhplus.be.server.coupon.usecase.command.MarkCouponUsedUseCase;
import kr.hhplus.be.server.coupon.usecase.query.GetAvailableCouponsUseCase;
import kr.hhplus.be.server.coupon.usecase.query.GetUserCouponsUseCase;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CouponFacade {

    private final IssueCouponUseCase issueCouponUseCase;
    private final MarkCouponUsedUseCase markCouponUsedUseCase;
    private final GetUserCouponsUseCase getUserCouponsUseCase;
    private final GetAvailableCouponsUseCase getAvailableCouponsUseCase;

    public CouponFacade(IssueCouponUseCase issueCouponUseCase,
                        MarkCouponUsedUseCase markCouponUsedUseCase,
                        GetUserCouponsUseCase getUserCouponsUseCase,
                        GetAvailableCouponsUseCase getAvailableCouponsUseCase) {
        this.issueCouponUseCase = issueCouponUseCase;
        this.markCouponUsedUseCase = markCouponUsedUseCase;
        this.getUserCouponsUseCase = getUserCouponsUseCase;
        this.getAvailableCouponsUseCase = getAvailableCouponsUseCase;
    }

    public void issue(Long userId, Long couponId) {
        issueCouponUseCase.issue(userId, couponId);
    }

    public void use(Long userId, Long couponId) {
        markCouponUsedUseCase.use(userId, couponId);
    }

    public List<UserCoupon> getUserCoupons(Long userId) {
        return getUserCouponsUseCase.getUserCoupons(userId);
    }

    public List<Coupon> getAvailableCoupons() {
        return getAvailableCouponsUseCase.getAvailableCoupons();
    }
}