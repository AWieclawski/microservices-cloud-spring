package edu.awieclawski.shop.adapters;

import edu.awieclawski.shop.configs.LoadBalancerConfiguration;
import edu.awieclawski.shop.configs.OrderServiceHystrixConfiguration;
import edu.awieclawski.shop.dtos.RequestOrderDto;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Enables getting data from 'order-service'
 * Endpoints must be the same as declared in the REST client of 'order-service'
 */
@FeignClient(name = "order-service", fallback = OrderServiceHystrixConfiguration.class)
@LoadBalancerClient(name = "order-service", configuration = LoadBalancerConfiguration.class)
public interface PurchaseOrderAdapter {

    @RequestMapping("/api/order/{id}")
    RequestOrderDto getOrderById(@PathVariable("id") Long id);

    @GetMapping("/api/orders")
    List<RequestOrderDto> getOrdersInfo(@RequestParam("ids") List<Long> ids);
}