package kr.hhplus.be.server.order.adapter.controller;

import kr.hhplus.be.server.order.usecase.dto.*;
import kr.hhplus.be.server.order.facade.OrderFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderFacade orderFacade;

    // 주문 생성
    @PostMapping
    public ResponseEntity<OrderResponse> placeOrder(@RequestBody OrderRequest request) {
        OrderResponse response = orderFacade.placeOrder(request);
        return ResponseEntity.ok(response);
    }

    // 주문 상세 조회
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrderDetail(@PathVariable Long orderId) {
        OrderResponse response = orderFacade.getOrderDetail(orderId);
        return ResponseEntity.ok(response);
    }

    // 주문 내역 조회 (by userId)
    @GetMapping
    public ResponseEntity<List<OrderSummaryResponse>> getOrdersByUserId(@RequestParam Long userId) {
        List<OrderSummaryResponse> responseList = orderFacade.getOrderList(userId);
        return ResponseEntity.ok(responseList);
    }

    // 주문 취소
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> cancelOrder(@PathVariable Long orderId) {
        orderFacade.cancelOrder(orderId);
        return ResponseEntity.noContent().build();
    }
}