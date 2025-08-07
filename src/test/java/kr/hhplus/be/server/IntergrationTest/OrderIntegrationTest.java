package kr.hhplus.be.server.IntergrationTest;

import kr.hhplus.be.server.item.usecase.dto.*;
import kr.hhplus.be.server.item.usecase.query.GetAllItemsUseCase;
import kr.hhplus.be.server.order.usecase.dto.*;
import kr.hhplus.be.server.order.facade.OrderFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class OrderIntegrationTest {

    @Autowired
    private OrderFacade orderFacade;

    @Autowired
    private GetAllItemsUseCase getItemUseCase;

    private Long itemOptionId;
    private Long itemId;
    private int originalStock;

    @BeforeEach
    void setUp() {
        ItemDetailResponse item = getItemUseCase.getAllItems().get(0);
        ItemDetailResponse.ItemOptionResponse option = item.options().get(0);

        this.itemId = option.optionId();
        this.itemOptionId = option.optionId();
        this.originalStock = option.quantity();
    }

    @Test
    @DisplayName("주문 생성 성공")
    void placeOrder_success() {
        OrderRequest request = new OrderRequest(1L, List.of(
                new OrderRequest.OrderItemRequest(itemOptionId, 2)
        ));

        OrderResponse response = orderFacade.placeOrder(request);

        assertThat(response).isNotNull();
        assertThat(response.orderId()).isNotNull();
        assertThat(response.items()).hasSize(1);
    }

    @Test
    @DisplayName("주문 상세 조회 성공")
    void getOrderDetail_success() {
        OrderRequest request = new OrderRequest(1L, List.of(
                new OrderRequest.OrderItemRequest(itemOptionId, 1)
        ));

        OrderResponse created = orderFacade.placeOrder(request);
        OrderResponse found = orderFacade.getOrderDetail(created.orderId());

        assertThat(found).isNotNull();
        assertThat(found.orderId()).isEqualTo(created.orderId());
    }

    @Test
    @DisplayName("주문 취소 시 재고 복원")
    void cancelOrder_restoreStock() {
        int orderQty = 3;
        OrderRequest request = new OrderRequest(1L, List.of(
                new OrderRequest.OrderItemRequest(itemOptionId, orderQty)
        ));

        OrderResponse response = orderFacade.placeOrder(request);
        orderFacade.cancelOrder(response.orderId());

        int currentQty = getItemUseCase.getAllItems().stream()
                .flatMap(i -> i.options().stream())
                .filter(opt -> opt.optionId().equals(itemOptionId))
                .mapToInt(ItemDetailResponse.ItemOptionResponse::quantity)
                .findFirst()
                .orElse(-1);

        assertThat(currentQty).isEqualTo(originalStock);
    }
}