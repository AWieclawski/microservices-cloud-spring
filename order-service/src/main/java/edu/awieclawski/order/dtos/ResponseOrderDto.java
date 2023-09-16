package edu.awieclawski.order.dtos;

import lombok.Builder;
import lombok.Value;

import java.time.Instant;

/**
 * Shortened Purchase Order data as the response to 'shop-service'
 */
@Value
@Builder
public class ResponseOrderDto {
    Long id;
    String subject;
    Integer quantity;
    Instant deliveryDate;
}
