package edu.awieclawski.payment.events;

import edu.awieclawski.payment.dtos.PaymentEventDto;
import edu.awieclawski.payment.ports.EventEmitter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class StreamEventEmitter implements EventEmitter {

    private static final String PAYMENTS_SHOP_BINDING_NAME = "payments-shop-out-0";
    private static final String PAYMENTS_ORDER_BINDING_NAME = "payments-order-out-0";
    private static final String EVENT_TYPE = "UPDATE";

    private final StreamBridge streamBridge;

    @Override
    public void emitToShop(PaymentEventDto event) {
        event.setType(EVENT_TYPE);
        streamBridge.send(PAYMENTS_SHOP_BINDING_NAME, event);
        log.info("Payment to shop: [{}] sent.", event);
    }

    @Override
    public void emitToOrder(PaymentEventDto event) {
        event.setType(EVENT_TYPE);
        streamBridge.send(PAYMENTS_ORDER_BINDING_NAME, event);
        log.info("Payment to order: [{}] sent.", event);
    }
}
