package kr.hhplus.be.server.IntergrationTest;

import kr.hhplus.be.server.item.usecase.command.RestoreItemStockUseCase;
import kr.hhplus.be.server.item.usecase.command.DecreaseStockUseCase;
import kr.hhplus.be.server.item.usecase.dto.ItemDetailResponse;
import kr.hhplus.be.server.item.usecase.query.GetItemUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class ItemIntegrationTest {

    @Autowired
    private GetItemUseCase getItemUseCase;

    @Autowired
    private DecreaseStockUseCase decreaseStockUseCase;

    @Autowired
    private RestoreItemStockUseCase restoreItemStockUseCase;

    private Long targetOptionId;
    private int originalStock;

    @BeforeEach
    void setUp() {
        List<ItemDetailResponse> items = getItemUseCase.getAllItems();
        assertThat(items).isNotEmpty();

        ItemDetailResponse.ItemOptionResponse option = items.get(0).options().get(0);
        targetOptionId = option.optionId();
        originalStock = option.quantity();
    }

    @Test
    @DisplayName("전체 상품 목록 조회 성공")
    void testGetAllItems() {
        List<ItemDetailResponse> items = getItemUseCase.getAllItems();

        assertThat(items).isNotEmpty();
        assertThat(items.get(0).options()).isNotEmpty();
    }

    @Test
    @DisplayName("재고 감소 및 복구 테스트")
    void testDecreaseAndRestoreStock() {
        int amount = 2;

        // 재고 감소
        decreaseStockUseCase.decrease(targetOptionId, amount);

        // 재고 복원
        restoreItemStockUseCase.restore(targetOptionId, amount);

        // 복원 검증
        List<ItemDetailResponse> items = getItemUseCase.getAllItems();
        int restored = items.stream()
                .flatMap(item -> item.options().stream())
                .filter(opt -> opt.optionId().equals(targetOptionId))
                .map(ItemDetailResponse.ItemOptionResponse::quantity)
                .findFirst()
                .orElseThrow();

        assertThat(restored).isEqualTo(originalStock);
    }
}