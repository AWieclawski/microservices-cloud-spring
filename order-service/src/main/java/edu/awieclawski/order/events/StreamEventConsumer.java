package edu.awieclawski.order.events;

import edu.awieclawski.order.dtos.PaymentEventDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Slf4j
@Component("paymentOrderEventsConsumer")
public class StreamEventConsumer
        implements Consumer<PaymentEventDto> {

    @Override
    public void accept(PaymentEventDto paymentEventDto) {
        log.info(" - ORDER - New payment update [id: {}, subject: {} , type: {}] ", paymentEventDto.getId(), paymentEventDto.getSubject(), paymentEventDto.getType());
    }
}
