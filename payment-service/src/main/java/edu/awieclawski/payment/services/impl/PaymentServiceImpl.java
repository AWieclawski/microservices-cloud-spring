package edu.awieclawski.payment.services.impl;

import edu.awieclawski.payment.dtos.PaymentDto;
import edu.awieclawski.payment.dtos.PaymentEventDto;
import edu.awieclawski.payment.enums.AssetSide;
import edu.awieclawski.payment.mappers.PaymentMapper;
import edu.awieclawski.payment.ports.EventEmitter;
import edu.awieclawski.payment.repositories.PaymentRepository;
import edu.awieclawski.payment.services.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Service
public class PaymentServiceImpl implements PaymentService {

    private final EventEmitter eventEmitter;
    private final PaymentRepository repository;
    private final PaymentMapper mapper;

    @Override
    public PaymentDto process(PaymentDto paymentRequest) {
        paymentRequest.setId(repository.generateId());
        var paymentEventDto = mapper.toEventDto(paymentRequest);
        emitterEvent(paymentRequest, paymentEventDto);
        log.info("Payment  [{}] created.", paymentRequest);
        return paymentRequest;
    }

    @Override
    public PaymentDto getPaymentById(Long id) throws Exception {
        return repository.getPaymentById(id).orElseThrow(Exception::new);
    }

    @Override
    public List<PaymentDto> getPayments(List<Long> ids) {
        return new ArrayList<>(repository.getPayments(ids));
    }


    private void emitterEvent(PaymentDto paymentRequest, PaymentEventDto paymentEventDto) {
        if (AssetSide.RECEIVABLE.equals(paymentRequest.getSide())) {
            eventEmitter.emitToShop(paymentEventDto);
        } else if (AssetSide.COMMITMENT.equals(paymentRequest.getSide())) {
            eventEmitter.emitToOrder(paymentEventDto);
        } else {
            log.error("No side for payment [{}] selected!", paymentRequest.getId());
        }
    }
}
