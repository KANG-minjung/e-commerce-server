package kr.hhplus.be.server.usecase;

import kr.hhplus.be.server.common.BusinessException;
import kr.hhplus.be.server.common.ErrorCode;
import kr.hhplus.be.server.coupon.domain.repository.CouponRepository;
import kr.hhplus.be.server.coupon.domain.repository.UserCouponRepository;
import kr.hhplus.be.server.item.domain.model.Item;
import kr.hhplus.be.server.item.domain.repository.ItemRepository;
import kr.hhplus.be.server.order.domain.model.Order;
import kr.hhplus.be.server.order.domain.model.OrderItem;
import kr.hhplus.be.server.order.domain.repository.OrderRepository;
import kr.hhplus.be.server.payment.adapter.external.FakeDataPlatformClient;
import kr.hhplus.be.server.payment.domain.model.Payment;
import kr.hhplus.be.server.payment.facade.PaymentFacade;
import kr.hhplus.be.server.payment.usecase.command.ProcessPaymentUseCase;
import kr.hhplus.be.server.payment.usecase.dto.PaymentRequest;
import kr.hhplus.be.server.support.TestReflectionUtils;
import kr.hhplus.be.server.user.domain.model.User;
import kr.hhplus.be.server.user.domain.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PaymentFacadeTest {

    private PaymentFacade facade;

    private ProcessPaymentUseCase processPaymentUseCase;
    private OrderRepository orderRepository;
    private UserRepository userRepository;
    private CouponRepository couponRepository;
    private UserCouponRepository userCouponRepository;
    private ItemRepository itemRepository;
    private FakeDataPlatformClient platformClient;

    @BeforeEach
    void setUp() {
        processPaymentUseCase = mock(ProcessPaymentUseCase.class);
        orderRepository = mock(OrderRepository.class);
        userRepository = mock(UserRepository.class);
        couponRepository = mock(CouponRepository.class);
        userCouponRepository = mock(UserCouponRepository.class);
        itemRepository = mock(ItemRepository.class);
        platformClient = mock(FakeDataPlatformClient.class);

        facade = new PaymentFacade(
                processPaymentUseCase, orderRepository, userRepository,
                couponRepository, userCouponRepository, itemRepository, platformClient
        );
    }

    @Test
    void 결제_성공_쿠폰_없음() {
        // given
        Long userId = 1L;
        Long orderId = 10L;

        Order order = new Order(userId, List.of(new OrderItem(100L, 2000, 2)));
        TestReflectionUtils.setId(order, 1L);

        User user = new User(userId, "홍길동", 10000);
        Item item = new Item(100L, "마우스", 10, 2000);


        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(itemRepository.findById(100L)).thenReturn(Optional.of(item));
        when(processPaymentUseCase.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        // when
        PaymentRequest request = new PaymentRequest(orderId, userId, null);
        Payment result = facade.process(request);

        // then
        assertThat(result.getFinalAmount()).isEqualTo(4000);
        assertThat(user.getBalance()).isEqualTo(6000);
        verify(platformClient).sendPaymentData(any());
    }

    @Test
    void 결제_실패_잔액_부족() {
        // given
        Long userId = 1L;
        Long orderId = 10L;

        Order order = new Order(userId, List.of(new OrderItem(100L, 3000, 2)));
        TestReflectionUtils.setId(order, 1L);
        User user = new User(userId, "부자", 2000);
        Item item = new Item(100L, "모니터", 5, 3000);

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(itemRepository.findById(100L)).thenReturn(Optional.of(item));

        // when & then
        PaymentRequest request = new PaymentRequest(orderId, userId, null);
        assertThatThrownBy(() -> facade.process(request))
                .isInstanceOf(BusinessException.class)
                .hasMessage(ErrorCode.BALANCE_INSUFFICIENT.getMessage());
    }

    private void setPrivateId(Object target, Long value) {
        try {
            Field field = target.getClass().getDeclaredField("id");
            field.setAccessible(true);
            field.set(target, value);
        } catch (Exception e) {
            throw new RuntimeException("ID 주입 실패", e);
        }
    }
}