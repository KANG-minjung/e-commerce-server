package kr.hhplus.be.server.multiThreadTest;

import kr.hhplus.be.server.item.domain.model.ItemStock;
import kr.hhplus.be.server.item.domain.repository.ItemStockRepository;
import kr.hhplus.be.server.item.usecase.command.DecreaseStockUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class DecreaseStockConcurrencyTest {

    @Autowired
    private DecreaseStockUseCase decreaseStockUseCase;

    @Autowired
    private ItemStockRepository stockRepository;

    @BeforeEach
    void setUp() {
        ItemStock stock = new ItemStock(1L, 100);
        stockRepository.save(stock);
    }

    @Test
    void 동시에_재고_감소_요청_시_정합성_유지() throws InterruptedException {
        int threadCount = 10;
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            executor.execute(() -> {
                try {
                    decreaseStockUseCase.decrease(1L, 1);
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        ItemStock stock = stockRepository.findByIdForUpdate(1L);
        assertThat(stock.getQuantity()).isEqualTo(90); // 100 - 10
    }
}