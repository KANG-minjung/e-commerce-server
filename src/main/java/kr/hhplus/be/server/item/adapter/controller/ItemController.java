package kr.hhplus.be.server.item.adapter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kr.hhplus.be.server.item.facade.ItemFacade;
import kr.hhplus.be.server.item.facade.OrderItemFacade;
import kr.hhplus.be.server.item.usecase.dto.*;
import kr.hhplus.be.server.item.usecase.query.GetAllItemsUseCase;
import kr.hhplus.be.server.item.usecase.query.GetItemByIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
@Validated
@Tag(name = "Item", description = "상품 관련 API")
public class ItemController {

    private final ItemFacade itemFacade;
    private final OrderItemFacade orderItemFacade;
    private final GetAllItemsUseCase getAllItemUseCase;
    private final GetItemByIdUseCase getItemByIdUseCase;

    @PostMapping
    @Operation(summary = "상품 생성")
    public ResponseEntity<ItemResponse> register(@RequestBody @Valid RegisterItemRequest request) {
        ItemResponse item = itemFacade.registerItem(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(item);
    }

    @PostMapping("/decrease")
    @Operation(summary = "재고 감소")
    public ResponseEntity<Void> decreaseStock(@RequestBody @Valid OrderItemStockRequest request) {
        orderItemFacade.orderItem(request.itemOptionId(), request.quantity());
        return ResponseEntity.ok().build();
    }

    // 상품의 조회는 굳이 Facade를 통해 조회하지 않아도 되기에 UseCase를 통해 호출
    // 전체 상품 조회
    @GetMapping
    @Operation(summary = "상품 목록 조회")
    public ResponseEntity<List<ItemDetailResponse>> getAllItems() {
        List<ItemDetailResponse> items = getAllItemUseCase.getAllItems();
        return ResponseEntity.ok(items);
    }

    // 단일 상품 조회
    @GetMapping("/{id}")
    @Operation(summary = "상품 상세 조회")
    public ResponseEntity<ItemDetailResponse> getItem(@PathVariable Long id) {
        ItemDetailResponse item = getItemByIdUseCase.getItemById(id);
        return ResponseEntity.ok(item);
    }
}
