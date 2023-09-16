package edu.awieclawski.payment.controllers;

import edu.awieclawski.payment.dtos.PaymentDto;
import edu.awieclawski.payment.services.PaymentService;
import edu.awieclawski.payment.web.LocationUri;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @RequestMapping("/payment/{id}")
    public PaymentDto getPaymentInfo(@PathVariable("id") Long id) throws Exception {
        return paymentService.getPaymentById(id);
    }

    @GetMapping("/payments")
    public Collection<PaymentDto> getPaymentsInfo(@RequestParam List<Long> ids) {
        return paymentService.getPayments(ids);
    }

    @PostMapping("/payment")
    public ResponseEntity<PaymentDto> create(@RequestBody PaymentDto paymentDto) {
        var paymentDomain = paymentService.process(paymentDto);
        var locationUri = LocationUri.fromRequest(paymentDomain.getId());
        return ResponseEntity.created(locationUri)
                .body(paymentDomain);
    }

}
