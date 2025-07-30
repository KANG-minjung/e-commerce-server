package kr.hhplus.be.server.payment.facade;

import kr.hhplus.be.server.common.BusinessException;
import kr.hhplus.be.server.common.ErrorCode;
import kr.hhplus.be.server.coupon.domain.model.Coupon;
import kr.hhplus.be.server.coupon.domain.model.UserCoupon;
import kr.hhplus.be.server.coupon.domain.repository.CouponRepository;
import kr.hhplus.be.server.coupon.domain.repository.UserCouponRepository;
import kr.hhplus.be.server.item.domain.model.Item;
import kr.hhplus.be.server.item.domain.repository.ItemRepository;
import kr.hhplus.be.server.order.domain.model.Order;
import kr.hhplus.be.server.order.domain.model.OrderItem;
import kr.hhplus.be.server.order.domain.repository.OrderRepository;
import kr.hhplus.be.server.payment.adapter.external.FakeDataPlatformClient;
import kr.hhplus.be.server.payment.domain.model.Payment;
import kr.hhplus.be.server.payment.usecase.command.ProcessPaymentUseCase;
import kr.hhplus.be.server.payment.usecase.dto.PaymentRequest;
import kr.hhplus.be.server.user.domain.model.User;
import kr.hhplus.be.server.user.domain.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class PaymentFacade {

    private final ProcessPaymentUseCase processPaymentUseCase;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CouponRepository couponRepository;
    private final UserCouponRepository userCouponRepository;
    private final ItemRepository itemRepository;
    private final FakeDataPlatformClient platformClient;

    public PaymentFacade(ProcessPaymentUseCase processPaymentUseCase,
                         OrderRepository orderRepository,
                         UserRepository userRepository,
                         CouponRepository couponRepository,
                         UserCouponRepository userCouponRepository,
                         ItemRepository itemRepository,
                         FakeDataPlatformClient platformClient) {
        this.processPaymentUseCase = processPaymentUseCase;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.couponRepository = couponRepository;
        this.userCouponRepository = userCouponRepository;
        this.itemRepository = itemRepository;
        this.platformClient = platformClient;
    }

    public Payment process(PaymentRequest request) {
        Order order = orderRepository.findById(request.orderId())
                .orElseThrow(() -> new BusinessException(ErrorCode.ORDER_NOT_FOUND));

        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_INVALID));

        int total = order.getItems().stream().mapToInt(this::calculateOrderItemPrice).sum();

        int finalAmount = applyCouponDiscount(request.userId(), request.couponId(), total);

        if (user.getBalance() < finalAmount) {
            throw new BusinessException(ErrorCode.BALANCE_INSUFFICIENT);
        }

        user.deduct(finalAmount);
        userRepository.save(user);

        Payment payment = new Payment(order.getId(), user.getId(), request.couponId(), finalAmount);
        Payment saved = processPaymentUseCase.save(payment);

        platformClient.sendPaymentData(saved);
        return saved;
    }

    private int calculateOrderItemPrice(OrderItem item) {
        Item realItem = itemRepository.findById(item.getItemId())
                .orElseThrow(() -> new BusinessException(ErrorCode.ITEM_NOT_FOUND));
        return realItem.getPrice() * item.getQuantity();
    }

    private int applyCouponDiscount(Long userId, Long couponId, int total) {
        if (couponId == null) return total;

        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow(() -> new BusinessException(ErrorCode.COUPON_NOT_FOUND));

        UserCoupon userCoupon = userCouponRepository.findByUserIdAndCouponId(userId, couponId)
                .orElseThrow(() -> new BusinessException(ErrorCode.COUPON_NOT_FOUND));

        userCoupon.validateAvailable();
        userCoupon.markUsed();
        userCouponRepository.save(userCoupon);

        return switch (coupon.getType()) {
            case RATE -> total - (int) (total * coupon.getDiscountRate());
            case FIXED -> total - (int) coupon.getDiscountRate();
        };
    }
}
