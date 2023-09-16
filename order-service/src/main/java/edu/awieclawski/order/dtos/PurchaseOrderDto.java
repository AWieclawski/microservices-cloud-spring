package edu.awieclawski.order.dtos;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

/**
 * Full Purchase Order data of items ordered from supplier of the Shop
 */
@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class PurchaseOrderDto {
    Long id;
    String name;
    String subject;
    Integer quantity;
    String address;
    BigDecimal dueAmount;
    Instant deliveryDate;
    Instant orderDate;
    List<Long> payments;
}
