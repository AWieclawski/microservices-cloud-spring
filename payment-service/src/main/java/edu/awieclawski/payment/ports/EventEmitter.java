package edu.awieclawski.payment.ports;

import edu.awieclawski.payment.dtos.PaymentEventDto;

public interface EventEmitter {

    void emitToShop(PaymentEventDto event);
    void emitToOrder(PaymentEventDto event);
}
