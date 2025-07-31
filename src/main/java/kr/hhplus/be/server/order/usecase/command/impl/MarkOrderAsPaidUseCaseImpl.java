package kr.hhplus.be.server.order.usecase.command.impl;

import kr.hhplus.be.server.order.domain.model.Order;
import kr.hhplus.be.server.order.domain.repository.OrderRepository;
import kr.hhplus.be.server.order.usecase.command.MarkOrderAsPaidUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MarkOrderAsPaidUseCaseImpl implements MarkOrderAsPaidUseCase {

    private final OrderRepository orderRepository;

    @Override
    public void execute(Order order) {
        order.markPaid();
        orderRepository.save(order);
    }
}
