package edu.awieclawski.shop.dtos;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

/**
 * Sales Order in shop-service as the result of the customer shopping.
 * Includes Purchase Orders list ordered from suppliers of the Shop
 */
@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class SalesOrderDto {
    Long id;
    String customer;
    String name;
    BigDecimal salesAmount;
    Instant saleDate;
    List<Long> purchaseOrders;
    List<Long> payments;
}
