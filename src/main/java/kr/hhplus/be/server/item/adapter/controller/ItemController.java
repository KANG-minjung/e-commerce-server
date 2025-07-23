package kr.hhplus.be.server.item.adapter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.hhplus.be.server.item.usecase.ItemFacade;
import kr.hhplus.be.server.item.usecase.dto.CreateItemRequest;
import kr.hhplus.be.server.item.usecase.dto.ItemResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
@Tag(name = "User", description = "사용자 관련 API")
public class ItemController {

    private final ItemFacade itemFacade;

    public ItemController(ItemFacade itemFacade) {
        this.itemFacade = itemFacade;
    }

    @PostMapping
    @Operation(summary = "상품 생성")
    public Long create(@RequestBody CreateItemRequest request) {
        return itemFacade.create(request);
    }

    @GetMapping("/{id}")
    @Operation(summary = "상품 개별 조회")
    public ItemResponse getOne(@PathVariable Long id) {
        return itemFacade.getByIdWithStatus(id);
    }

    @GetMapping
    @Operation(summary = "상품 목록 조회")
    public List<ItemResponse> getAll() {
        return itemFacade.getAllWithStatus();
    }
}
