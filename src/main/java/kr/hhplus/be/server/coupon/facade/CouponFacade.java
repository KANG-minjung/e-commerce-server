package kr.hhplus.be.server.coupon.facade;

import kr.hhplus.be.server.coupon.domain.model.Coupon;
import kr.hhplus.be.server.coupon.domain.model.UserCoupon;
import kr.hhplus.be.server.coupon.usecase.command.IssueCouponUseCase;
import kr.hhplus.be.server.coupon.usecase.command.UseCouponUseCase;
import kr.hhplus.be.server.coupon.usecase.query.GetAvailableCouponsUseCase;
import kr.hhplus.be.server.coupon.usecase.query.GetUserCouponsUseCase;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CouponFacade {

    private final IssueCouponUseCase issueCouponUseCase;
    private final UseCouponUseCase useCouponUseCase;
    private final GetUserCouponsUseCase getUserCouponsUseCase;
    private final GetAvailableCouponsUseCase getAvailableCouponsUseCase;

    public CouponFacade(IssueCouponUseCase issueCouponUseCase,
                        UseCouponUseCase useCouponUseCase,
                        GetUserCouponsUseCase getUserCouponsUseCase,
                        GetAvailableCouponsUseCase getAvailableCouponsUseCase) {
        this.issueCouponUseCase = issueCouponUseCase;
        this.useCouponUseCase = useCouponUseCase;
        this.getUserCouponsUseCase = getUserCouponsUseCase;
        this.getAvailableCouponsUseCase = getAvailableCouponsUseCase;
    }

    public void issue(Long userId, Long couponId) {
        issueCouponUseCase.issue(userId, couponId);
    }

    public void use(Long userId, Long couponId) {
        useCouponUseCase.use(userId, couponId);
    }

    public List<UserCoupon> getUserCoupons(Long userId) {
        return getUserCouponsUseCase.getUserCoupons(userId);
    }

    public List<Coupon> getAvailableCoupons() {
        return getAvailableCouponsUseCase.getAvailableCoupons();
    }
}