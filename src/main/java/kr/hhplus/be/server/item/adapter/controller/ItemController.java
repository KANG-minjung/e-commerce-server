package kr.hhplus.be.server.item.adapter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.hhplus.be.server.item.domain.model.Item;
import kr.hhplus.be.server.item.facade.ItemFacade;
import kr.hhplus.be.server.item.usecase.dto.CreateItemRequest;
import kr.hhplus.be.server.item.usecase.dto.ItemResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
@Tag(name = "Item", description = "상품 관련 API")
public class ItemController {

    private final ItemFacade facade;

    public ItemController(ItemFacade itemFacade) {
        this.facade = itemFacade;
    }

    @PostMapping
    @Operation(summary = "상품 생성")
    public ResponseEntity<ItemResponse> create(@RequestBody CreateItemRequest request) {
        Item item = facade.create(request.itemNm(), request.price(), request.quantity());
        return ResponseEntity.ok(toResponse(item));
    }

    @GetMapping("/{id}")
    @Operation(summary = "상품 개별 조회")
    public ResponseEntity<ItemResponse> get(@PathVariable Long id) {
        Item item = facade.getById(id);
        return ResponseEntity.ok(toResponse(item));
    }

    @GetMapping
    @Operation(summary = "상품 목록 조회")
    public ResponseEntity<List<ItemResponse>> getAll() {
        List<ItemResponse> items = facade.getAll().stream()
                .map(this::toResponse)
                .toList();
        return ResponseEntity.ok(items);
    }

    private ItemResponse toResponse(Item item) {
        return new ItemResponse(
                item.getId(),
                item.getName(),
                item.getPrice(),
                item.getQuantity(),
                item.isSoldOut() ? "SOLD_OUT" : "AVAILABLE"
        );
    }
}
