package edu.awieclawski.shop.configs;

import edu.awieclawski.shop.dtos.PaymentEventDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Slf4j
@Configuration
public class ShopConfiguration {

//    @Bean // cloud.function.definition: paymentBinding
//    public Consumer<PaymentEventDto> paymentBinding() {
//    public Consumer<PaymentEventDto> paymentEventsConsumer() {
//        return event -> log.info(" -- -- New payment update: {} {}", event.getId(), event.getSubject());
//    }
}
