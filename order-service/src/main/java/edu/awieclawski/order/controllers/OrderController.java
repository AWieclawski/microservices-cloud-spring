package edu.awieclawski.order.controllers;

import edu.awieclawski.order.dtos.PurchaseOrderDto;
import edu.awieclawski.order.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderRepository repository;

    @RequestMapping("/order/{id}")
    public Optional<PurchaseOrderDto> getOrderInfo(@PathVariable("id") Long id) {
        return repository.getOrderById(id);
    }

    @GetMapping("/orders")
    public Collection<PurchaseOrderDto> getOrdersInfo(@RequestParam List<Long> ids) {
        return repository.getOrders(ids);
    }

    @PostMapping("/order")
    public PurchaseOrderDto createOrder(@RequestBody PurchaseOrderDto order) {
        Instant orderDate = Instant.from(LocalDate.now());
        // may map PurchaseOrderDto and save created object to database
        return PurchaseOrderDto.builder()
                .id(repository.generateId())
                .name(order.getName())
                .subject(order.getSubject())
                .address(order.getAddress())
                .dueAmount(order.getDueAmount())
                .deliveryDate(order.getDeliveryDate())
                .orderDate(orderDate)
                .build();
    }

}
