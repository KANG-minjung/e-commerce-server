package kr.hhplus.be.server.payment.adapter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.hhplus.be.server.payment.domain.model.Payment;
import kr.hhplus.be.server.payment.facade.PaymentFacade;
import kr.hhplus.be.server.payment.usecase.dto.PaymentRequest;
import kr.hhplus.be.server.payment.usecase.dto.PaymentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentFacade paymentFacade;

    @PostMapping
    public ResponseEntity<PaymentResponse> pay(@RequestBody PaymentRequest request) {
        PaymentResponse response = paymentFacade.pay(request);
        return ResponseEntity.ok(response);
    }
}
