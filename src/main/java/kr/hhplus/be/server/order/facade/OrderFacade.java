package kr.hhplus.be.server.order.facade;

import kr.hhplus.be.server.common.BusinessException;
import kr.hhplus.be.server.common.ErrorCode;
import kr.hhplus.be.server.item.domain.model.Item;
import kr.hhplus.be.server.item.domain.repository.ItemRepository;
import kr.hhplus.be.server.item.usecase.command.DecreaseStockUseCase;
import kr.hhplus.be.server.order.domain.model.Order;
import kr.hhplus.be.server.order.usecase.command.CreateOrderUseCase;
import kr.hhplus.be.server.order.usecase.dto.OrderCreateCommand;
import kr.hhplus.be.server.order.usecase.dto.OrderRequest;
import kr.hhplus.be.server.order.usecase.dto.OrderResponse;
import kr.hhplus.be.server.order.usecase.query.GetOrderUseCase;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderFacade {

    private final CreateOrderUseCase createOrderUseCase;
    private final GetOrderUseCase getOrderUseCase;
    private final ItemRepository itemRepository;
//    private final DecreaseStockUseCase decreaseStockUseCase;

    public OrderFacade(CreateOrderUseCase createOrderUseCase,
                       GetOrderUseCase getOrderUseCase,
                       ItemRepository itemRepository) {
        this.createOrderUseCase = createOrderUseCase;
        this.getOrderUseCase = getOrderUseCase;
        this.itemRepository = itemRepository;
//        this.decreaseStockUseCase = decreaseStockUseCase;
    }

    public Order createOrder(Long userId, List<OrderCreateCommand.ItemOrderLine> itemRequests) {
        // 1. 상품 존재 여부 및 재고 확인 + 가격 조회
        List<OrderCreateCommand.ItemOrderLine> validatedItems = itemRequests.stream()
                .map(req -> {
                    Item item = itemRepository.findById(req.itemId())
                            .orElseThrow(() -> new BusinessException(ErrorCode.ITEM_NOT_FOUND));

//                    if (item.getQuantity() < req.quantity()) {
//                        throw new BusinessException(ErrorCode.ITEM_STOCK_INSUFFICIENT);
//                    }

                    // 재고 차감은 UseCase 통해 수행
//                    decreaseStockUseCase.decrease(item.getId(), req.quantity());

                    return new OrderCreateCommand.ItemOrderLine(item.getId(), item.getPrice(), req.quantity());
                }).toList();

        // 2. 주문 생성
        OrderCreateCommand command = new OrderCreateCommand(userId, validatedItems);
        return createOrderUseCase.create(command);
    }

    public Order getOrder(Long orderId) {
        return getOrderUseCase.getById(orderId);
    }

    public List<Order> getOrdersByUser(Long userId) {
        return getOrderUseCase.getByUserId(userId);
    }
}