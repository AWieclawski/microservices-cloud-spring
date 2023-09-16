package edu.awieclawski.shop.events;

import edu.awieclawski.shop.dtos.PaymentEventDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Slf4j
@Component("paymentShopEventsConsumer")
public class StreamEventConsumer
        implements Consumer<PaymentEventDto> {

    @Override
    public void accept(PaymentEventDto paymentEventDto) {
        log.info(" - SHOP - New payment update [id: {}, subject: {} , type: {}] ", paymentEventDto.getId(), paymentEventDto.getSubject(), paymentEventDto.getType());
    }
}
