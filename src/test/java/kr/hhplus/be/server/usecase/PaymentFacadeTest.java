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

}