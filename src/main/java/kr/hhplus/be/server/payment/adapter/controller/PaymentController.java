package kr.hhplus.be.server.payment.adapter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.hhplus.be.server.payment.domain.model.Payment;
import kr.hhplus.be.server.payment.facade.PaymentFacade;
import kr.hhplus.be.server.payment.usecase.dto.PaymentRequest;
import kr.hhplus.be.server.payment.usecase.dto.PaymentResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
@Tag(name = "Payment", description = "결제 관련 API")
public class PaymentController {

    private final PaymentFacade facade;

    public PaymentController(PaymentFacade facade) {
        this.facade = facade;
    }

    @PostMapping
    @Operation(summary = "결제")
    public ResponseEntity<PaymentResult> pay(@RequestBody PaymentRequest request) {
        Payment payment = facade.process(request);
        return ResponseEntity.ok(new PaymentResult(
                payment.getId(), payment.getFinalAmount(), payment.getPaidAt()
        ));
    }
}
