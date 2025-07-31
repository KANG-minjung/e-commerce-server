package kr.hhplus.be.server.IntergrationTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.hhplus.be.server.payment.usecase.dto.PaymentRequest;
import kr.hhplus.be.server.user.domain.model.User;
import kr.hhplus.be.server.user.domain.repository.UserRepository;
import kr.hhplus.be.server.order.domain.model.Order;
import kr.hhplus.be.server.order.domain.repository.OrderRepository;
import kr.hhplus.be.server.payment.domain.repository.PaymentRepository;
import kr.hhplus.be.server.coupon.domain.repository.UserCouponRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class PaymentIntegrationTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;

    @Autowired private UserRepository userRepository;
    @Autowired private OrderRepository orderRepository;
    @Autowired private PaymentRepository paymentRepository;
    @Autowired private UserCouponRepository userCouponRepository;

    private Long userId;
    private Long orderId;
    private Long couponId;

    @BeforeEach
    void setUp() {
        User user = userRepository.save(new User("1L", 10_000));
        this.userId = user.getId();

        Order order = orderRepository.save(new Order(userId, 10_000));
        this.orderId = order.getId();

        // 쿠폰도 발급되었다고 가정한 경우라면
        // userCouponRepository.save(...) 등으로 쿠폰 발급 처리 필요
    }

    @Test
    void 결제를_정상적으로_진행한다() throws Exception {
        PaymentRequest request = new PaymentRequest(userId, orderId, couponId); // couponId는 null 가능

        mockMvc.perform(post("/api/payments")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        assertThat(paymentRepository.findAll()).hasSize(1);

        User updatedUser = userRepository.findById(userId).orElseThrow();
        assertThat(updatedUser.getBalance()).isEqualTo(0);

        Order updatedOrder = orderRepository.findById(orderId).orElseThrow();
        assertThat(updatedOrder.getStatus().isPaid()).isTrue();
    }
}