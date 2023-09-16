package edu.awieclawski.payment.services;

import edu.awieclawski.payment.dtos.PaymentDto;

import java.util.List;

public interface PaymentService {

    PaymentDto process(PaymentDto paymentRequest);

    PaymentDto getPaymentById(Long id) throws Exception;

    List<PaymentDto> getPayments(List<Long> ids);
}
