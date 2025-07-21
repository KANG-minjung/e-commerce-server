package kr.hhplus.be.server.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.hhplus.be.server.controller.dto.MockItemDto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/mock/item")
@Tag(name = "상품 API", description = "상품 목록, 상세, 인기상품 조회 API")
public class ItemController {

    // 상품 목록 조회
    @Operation(summary = "상품 목록 조회", description = "전체 상품을 조회합니다.")
    @GetMapping("/itemList")
    public ResponseEntity<ItemResponseDto> getItemList(@RequestBody Map<String, Object> request){

        ItemDetailResponseDto responseDto1 = new ItemDetailResponseDto(1L, "상품1", 100, 30);
        ItemDetailResponseDto responseDto2 = new ItemDetailResponseDto(2L, "상품2", 150, 40);

        return ResponseEntity.ok(new ItemResponseDto(List.of(responseDto1, responseDto2)));
    }

    // 인기 상품 목록 조회
    @Operation(summary = "인기 상품 목록 조회", description = "인기 상품 목록을 조회합니다.")
    @GetMapping("/popularList")
    public ResponseEntity<ItemResponseDto> getItemSpecList(@RequestBody Map<String, Object> request){

        ItemDetailResponseDto responseDto1 = new ItemDetailResponseDto(1L, "상품1", 100, 30);
        ItemDetailResponseDto responseDto2 = new ItemDetailResponseDto(2L, "상품2", 150, 40);

        // 쿼리로 조회 하여 여기서는 표현할 수 없음..
        return ResponseEntity.ok(new ItemResponseDto(List.of(responseDto1, responseDto2)));
    }

    // 인기 상품 목록 조회
    @Operation(summary = "상품 상세 조회", description = "상품 상세내용을 조회합니다.")
    @GetMapping("/{itemId}")
    public ResponseEntity<ItemDetailResponseDto> getItemDetail(@PathVariable Long itemId){

        return ResponseEntity.ok(new ItemDetailResponseDto(itemId, "상품3", 100, 30));
    }
}
