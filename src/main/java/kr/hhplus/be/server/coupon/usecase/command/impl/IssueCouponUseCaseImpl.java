package kr.hhplus.be.server.coupon.usecase.command.impl;

import kr.hhplus.be.server.common.BusinessException;
import kr.hhplus.be.server.common.ErrorCode;
import kr.hhplus.be.server.coupon.domain.model.Coupon;
import kr.hhplus.be.server.coupon.domain.model.UserCoupon;
import kr.hhplus.be.server.coupon.domain.repository.CouponRepository;
import kr.hhplus.be.server.coupon.domain.repository.UserCouponRepository;
import kr.hhplus.be.server.coupon.usecase.command.IssueCouponUseCase;
import kr.hhplus.be.server.item.domain.model.Item;
import kr.hhplus.be.server.item.domain.repository.ItemRepository;
import org.springframework.stereotype.Service;

@Service
public class IssueCouponUseCaseImpl implements IssueCouponUseCase {

    private final CouponRepository couponRepository;
    private final UserCouponRepository userCouponRepository;

    public IssueCouponUseCaseImpl(CouponRepository couponRepository,
                                  UserCouponRepository userCouponRepository) {
        this.couponRepository = couponRepository;
        this.userCouponRepository = userCouponRepository;
    }

    @Override
    public void issue(Long userId, Long couponId) {
        if (userCouponRepository.findByUserIdAndCouponId(userId, couponId).isPresent()) {
            throw new BusinessException(ErrorCode.COUPON_ALREADY_ISSUED);
        }

        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow(() -> new BusinessException(ErrorCode.COUPON_NOT_FOUND));

        coupon.issue(); // 수량 제한 체크 + 증가
        couponRepository.save(coupon);

        userCouponRepository.save(new UserCoupon(userId, couponId));
    }
}
