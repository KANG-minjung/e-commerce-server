package kr.hhplus.be.server.order.adapter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.hhplus.be.server.order.domain.model.Order;
import kr.hhplus.be.server.order.facade.OrderFacade;
import kr.hhplus.be.server.order.usecase.dto.OrderCreateCommand;
import kr.hhplus.be.server.order.usecase.dto.OrderRequest;
import kr.hhplus.be.server.order.usecase.dto.OrderResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@Tag(name = "Order", description = "주문 관련 API")
public class OrderController {

    private final OrderFacade facade;

    public OrderController(OrderFacade facade) {
        this.facade = facade;
    }

    @PostMapping
    @Operation(summary = "주문 생성")
    public ResponseEntity<OrderResponse> create(@RequestBody OrderRequest request) {
        List<OrderCreateCommand.ItemOrderLine> items = request.items().stream()
                .map(i -> new OrderCreateCommand.ItemOrderLine(i.itemId(), 0, i.quantity())) // price는 내부에서 조회
                .toList();

        Order order = facade.createOrder(request.userId(), items);

        return ResponseEntity.ok(toResponse(order));
    }

    @GetMapping("/{id}")
    @Operation(summary = "주문 상세 조회")
    public ResponseEntity<OrderResponse> getById(@PathVariable Long id) {
        Order order = facade.getOrder(id);
        return ResponseEntity.ok(toResponse(order));
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "사용자 별 주문 목록 조회")
    public ResponseEntity<List<OrderResponse>> getByUser(@PathVariable Long userId) {
        List<Order> orders = facade.getOrdersByUser(userId);
        return ResponseEntity.ok(orders.stream().map(this::toResponse).toList());
    }

    private OrderResponse toResponse(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getUserId(),
                order.getTotalPrice(),
                order.getCreatedAt(),
                order.getItems().stream()
                        .map(i -> new OrderResponse.OrderItemResponse(
                                i.getItemId(), i.getPrice(), i.getQuantity()))
                        .toList()
        );
    }
}