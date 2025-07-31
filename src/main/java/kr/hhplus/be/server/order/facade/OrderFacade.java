package kr.hhplus.be.server.order.facade;

import org.springframework.transaction.annotation.Transactional;
import kr.hhplus.be.server.common.BusinessException;
import kr.hhplus.be.server.common.ErrorCode;
import kr.hhplus.be.server.item.domain.model.Item;
import kr.hhplus.be.server.item.domain.repository.ItemRepository;
import kr.hhplus.be.server.item.usecase.command.DecreaseStockUseCase;
import kr.hhplus.be.server.order.domain.model.Order;
import kr.hhplus.be.server.order.usecase.command.CancelOrderUseCase;
import kr.hhplus.be.server.order.usecase.command.CreateOrderUseCase;
import kr.hhplus.be.server.order.usecase.dto.OrderCreateCommand;
import kr.hhplus.be.server.order.usecase.dto.OrderRequest;
import kr.hhplus.be.server.order.usecase.dto.OrderResponse;
import kr.hhplus.be.server.order.usecase.dto.OrderSummaryResponse;
import kr.hhplus.be.server.order.usecase.query.GetOrderUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderFacade {

    private final CreateOrderUseCase createOrderUseCase;
    private final GetOrderUseCase getOrderUseCase;
    private final CancelOrderUseCase cancelOrderUseCase;

    @Transactional
    public OrderResponse placeOrder(OrderRequest request) {
        return createOrderUseCase.create(request);
    }

    @Transactional(readOnly = true)
    public OrderResponse getOrderDetail(Long orderId) {
        return getOrderUseCase.getById(orderId);
    }

    @Transactional(readOnly = true)
    public List<OrderSummaryResponse> getOrderList(Long userId) {
        return getOrderUseCase.getAllByUserId(userId);
    }

    @Transactional
    public void cancelOrder(Long orderId) {
        cancelOrderUseCase.cancel(orderId);
    }
}
