package kr.hhplus.be.server.coupon.usecase;

import kr.hhplus.be.server.common.BusinessException;
import kr.hhplus.be.server.common.ErrorCode;
import kr.hhplus.be.server.coupon.domain.model.Coupon;
import kr.hhplus.be.server.coupon.domain.model.UserCoupon;
import kr.hhplus.be.server.coupon.domain.repository.CouponRepository;
import kr.hhplus.be.server.coupon.domain.repository.UserCouponRepository;
import kr.hhplus.be.server.coupon.usecase.dto.IssueCouponRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CouponCommandUseCaseImpl implements CouponCommandUseCase {
    private final CouponRepository couponRepository;
    private final UserCouponRepository userCouponRepository;

    public CouponCommandUseCaseImpl(CouponRepository couponRepository, UserCouponRepository userCouponRepository) {
        this.couponRepository = couponRepository;
        this.userCouponRepository = userCouponRepository;
    }

    @Override
    public void issue(IssueCouponRequest request) {
        Coupon coupon = couponRepository.findById(request.couponId())
                .orElseThrow(() -> new BusinessException(ErrorCode.COUPON_NOT_FOUND));
        coupon.validateIssueable();

        boolean alreadyIssued = userCouponRepository.findByUserIdAndCouponId(request.userId(), request.couponId()).isPresent();
        if (alreadyIssued) throw new BusinessException(ErrorCode.COUPON_ALREADY_ISSUED);

        coupon.increaseIssuedCount();
        couponRepository.save(coupon);

        userCouponRepository.save(new UserCoupon(
                null,
                request.userId(),
                request.couponId(),
                "AVAILABLE",
                LocalDateTime.now(),
                null
        ));
    }

    @Override
    public void markUsed(Long userId, Long couponId) {
        UserCoupon uc = userCouponRepository.findByUserIdAndCouponId(userId, couponId)
                .orElseThrow(() -> new BusinessException(ErrorCode.COUPON_NOT_FOUND));
        uc.validateAvailable();
        uc.markUsed();
        userCouponRepository.save(uc);
    }
}
