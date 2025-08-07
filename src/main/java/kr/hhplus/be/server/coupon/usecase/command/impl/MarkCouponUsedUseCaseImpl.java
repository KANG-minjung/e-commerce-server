package kr.hhplus.be.server.coupon.usecase.command.impl;

import kr.hhplus.be.server.common.BusinessException;
import kr.hhplus.be.server.common.ErrorCode;
import kr.hhplus.be.server.coupon.domain.model.UserCoupon;
import kr.hhplus.be.server.coupon.domain.repository.UserCouponRepository;
import kr.hhplus.be.server.coupon.usecase.command.MarkCouponUsedUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MarkCouponUsedUseCaseImpl implements MarkCouponUsedUseCase {

    private final UserCouponRepository repository;

    @Override
    public void use(Long userId, Long couponId) {
        UserCoupon uc = repository.findByUser_IdAndCoupon_Id(userId, couponId)
                .orElseThrow(() -> new BusinessException(ErrorCode.COUPON_NOT_FOUND));
        uc.markUsed();
        repository.save(uc);
    }
}