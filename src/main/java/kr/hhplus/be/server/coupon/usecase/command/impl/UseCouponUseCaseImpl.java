package kr.hhplus.be.server.coupon.usecase.command.impl;

import kr.hhplus.be.server.common.BusinessException;
import kr.hhplus.be.server.common.ErrorCode;
import kr.hhplus.be.server.coupon.domain.model.UserCoupon;
import kr.hhplus.be.server.coupon.domain.repository.UserCouponRepository;
import kr.hhplus.be.server.coupon.usecase.command.UseCouponUseCase;
import kr.hhplus.be.server.item.domain.model.Item;
import kr.hhplus.be.server.item.domain.repository.ItemRepository;
import org.springframework.stereotype.Service;

@Service
public class UseCouponUseCaseImpl implements UseCouponUseCase {

    private final UserCouponRepository repository;

    public UseCouponUseCaseImpl(UserCouponRepository repository) {
        this.repository = repository;
    }

    @Override
    public void use(Long userId, Long couponId) {
        UserCoupon uc = repository.findByUserIdAndCouponId(userId, couponId)
                .orElseThrow(() -> new BusinessException(ErrorCode.COUPON_NOT_FOUND));
        uc.markUsed();
        repository.save(uc);
    }
}