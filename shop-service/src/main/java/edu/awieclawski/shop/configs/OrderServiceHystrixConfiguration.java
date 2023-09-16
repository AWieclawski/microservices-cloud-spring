package edu.awieclawski.shop.configs;

import edu.awieclawski.shop.adapters.PurchaseOrderAdapter;
import edu.awieclawski.shop.caches.PurchaseOrdersCache;
import edu.awieclawski.shop.dtos.RequestOrderDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderServiceHystrixConfiguration implements PurchaseOrderAdapter {

    private final PurchaseOrdersCache ordersCache;

    @Override
    public RequestOrderDto getOrderById(Long id) {
        return fallBackOrderById(id);
    }

    @Override
    public List<RequestOrderDto> getOrdersInfo(List<Long> ids) {
        return fallBackOrdersInfo(ids);
    }

    private RequestOrderDto fallBackOrderById(Long id) {
        log.warn("Request failed! Try to get order by id: {} from cache", id);
        Optional<RequestOrderDto> request = ordersCache.getOrderById(id);
        return request.orElse(new RequestOrderDto());
    }

    private List<RequestOrderDto> fallBackOrdersInfo(List<Long> ids) {
        log.warn("Request failed! Try to get orders by ids: {} from cache", ids);
        var requests = ordersCache.getOrders(ids);
        return new ArrayList<>(requests);
    }
}
