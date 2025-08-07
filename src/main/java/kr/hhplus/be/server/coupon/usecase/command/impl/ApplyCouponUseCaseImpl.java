package kr.hhplus.be.server.coupon.usecase.command.impl;

import kr.hhplus.be.server.common.BusinessException;
import kr.hhplus.be.server.common.ErrorCode;
import kr.hhplus.be.server.coupon.domain.model.Coupon;
import kr.hhplus.be.server.coupon.domain.model.UserCoupon;
import kr.hhplus.be.server.coupon.domain.repository.UserCouponRepository;
import kr.hhplus.be.server.coupon.usecase.command.ApplyCouponUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplyCouponUseCaseImpl implements ApplyCouponUseCase {

    private final UserCouponRepository repository;

    @Override
    public int executeIfPresent(Long couponId, int price) {
        if (couponId == null) return 0;

        UserCoupon userCoupon = repository.findByIdForUpdate(couponId)
                .orElseThrow(() -> new BusinessException(ErrorCode.COUPON_NOT_FOUND));

        Coupon coupon = userCoupon.getCoupon();
        coupon.use();  // 쿠폰 상태 변경
        repository.save(userCoupon); // 변경된 Coupon 포함 저장 (Cascade 여부에 따라)

        return coupon.calculateDiscount(price);
    }
}