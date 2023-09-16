package edu.awieclawski.shop.controllers;

import edu.awieclawski.shop.dtos.PaymentEventDto;
import edu.awieclawski.shop.dtos.RequestOrderDto;
import edu.awieclawski.shop.dtos.SalesOrderDto;
import edu.awieclawski.shop.repositories.SalesOrderRepository;
import edu.awieclawski.shop.services.SalesOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import org.springframework.messaging.Message;

import java.time.Instant;
import java.util.List;
import java.util.function.Consumer;

@Slf4j
@RestController
@RequestMapping("shop")
@RequiredArgsConstructor
public class ShopController {

    private final SalesOrderService service;

    private final SalesOrderRepository repository;

    @RequestMapping("/{saleId}")
    public SalesOrderDto getSalesOrderById(@PathVariable("saleId") Long saleId) {
        return service.getSalesOrder(saleId);
    }

    @GetMapping("/sales")
    public List<SalesOrderDto> getSalesOrderById(@RequestParam("ids") List<Long> ids) {
        return service.getSalesOrders(ids);
    }

    @PostMapping("/salesorder")
    public SalesOrderDto createOrder(@RequestBody SalesOrderDto order) {
        // may map PurchaseOrderDto and save created object to database
        return SalesOrderDto.builder()
                .id(repository.generateId())
                .name(order.getName())
                .customer(order.getCustomer())
                .salesAmount(order.getSalesAmount())
                .saleDate(order.getSaleDate())
                .build();
    }

    @RequestMapping("/{saleId}/orders/delivery_date")
    public List<Instant> getOrderDeliveryDatesByShoppingId(@PathVariable("saleId") Long saleId) {
        return service.getOrderDeliveryDatesByShoppingId(saleId);
    }

    @RequestMapping("/{saleId}/orders")
    public List<RequestOrderDto> getOrdersByShoppingId(@PathVariable("saleId") Long saleId) {
        return service.getOrdersByShoppingId(saleId);
    }
}
