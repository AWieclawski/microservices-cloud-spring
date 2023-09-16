package edu.awieclawski.order.adapters;

import edu.awieclawski.order.dtos.ResponseOrderDto;
import edu.awieclawski.order.mappers.PurchaseOrderMapper;
import edu.awieclawski.order.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rest API to expose end-points of methods for any Feign client of cooperating applications
 */
@Slf4j
@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class PurchaseOrderApi {

    private final PurchaseOrderMapper mapper;

    private final OrderRepository repository;

    @RequestMapping("/order/{id}")
    public ResponseOrderDto getOrderInfo(@PathVariable("id") Long id) {
        return mapper.optionalToSharedDto(repository.getOrderById(id));
    }

    @GetMapping("/orders")
    public List<ResponseOrderDto> getOrdersInfo(@RequestParam List<Long> ids) {
        return mapper.toSharedDtos(repository.getOrders(ids));
    }


}
