package kr.hhplus.be.server.coupon.usecase.command.impl;

import kr.hhplus.be.server.common.BusinessException;
import kr.hhplus.be.server.common.ErrorCode;
import kr.hhplus.be.server.coupon.domain.model.Coupon;
import kr.hhplus.be.server.coupon.domain.model.UserCoupon;
import kr.hhplus.be.server.coupon.domain.repository.UserCouponRepository;
import kr.hhplus.be.server.coupon.usecase.command.UseCouponUseCase;
import kr.hhplus.be.server.item.domain.model.Item;
import kr.hhplus.be.server.item.domain.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UseCouponUseCaseImpl implements UseCouponUseCase {

    private final UserCouponRepository repository;

    @Override
    public void use(Long userId, Long couponId) {
        UserCoupon uc = repository.findByUser_IdAndCoupon_Id(userId, couponId)
                .orElseThrow(() -> new BusinessException(ErrorCode.COUPON_NOT_FOUND));
        uc.markUsed();
        repository.save(uc);
    }

    @Override
    public int executeIfPresent(Long couponId, int price) {
        if (couponId == null) return 0;

        UserCoupon userCoupon = repository.findByIdForUpdate(couponId)
                .orElseThrow(() -> new BusinessException(ErrorCode.COUPON_NOT_FOUND));

        Coupon coupon = userCoupon.getCoupon(); // ✅ 여기서 Coupon 엔티티 추출

        coupon.use();
        repository.save(userCoupon);  // 또는 repository.save(coupon) → 저장 전략에 따라

        return coupon.calculateDiscount(price);
    }
}