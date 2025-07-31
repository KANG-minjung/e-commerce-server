package kr.hhplus.be.server.order.usecase.command;

import kr.hhplus.be.server.order.domain.model.Order;

public interface MarkOrderAsPaidUseCase {
    void execute(Order order);
}
