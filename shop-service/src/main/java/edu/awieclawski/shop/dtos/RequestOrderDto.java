package edu.awieclawski.shop.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * Mirror DTO from 'order-service' - it enables an easy deserialization
 *
 * @Value annotation is prohibited - it disables deserialization
 * and cause com.fasterxml.jackson.databind.exc.InvalidDefinitionException
 */
@Data
@NoArgsConstructor
public class RequestOrderDto {
    private Long id;
    private String subject;
    private Integer quantity;
    private Instant deliveryDate;
}
