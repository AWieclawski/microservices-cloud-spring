package edu.awieclawski.shop.services.impl;

import edu.awieclawski.shop.adapters.PurchaseOrderAdapter;
import edu.awieclawski.shop.caches.PurchaseOrdersCache;
import edu.awieclawski.shop.dtos.RequestOrderDto;
import edu.awieclawski.shop.dtos.SalesOrderDto;
import edu.awieclawski.shop.repositories.SalesOrderRepository;
import edu.awieclawski.shop.services.SalesOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SalesOrderServiceImpl implements SalesOrderService {

    private final SalesOrderRepository repository;
    private final PurchaseOrderAdapter purchaseOrderAdapter;
    private final PurchaseOrdersCache ordersCache;

    @Override
    public SalesOrderDto getSalesOrder(Long id) {
        return repository.getSalesOrderById(id)
                .orElse(null);
    }

    @Override
    public List<SalesOrderDto> getSalesOrders(List<Long> ids) {
        return new ArrayList<>(repository.getSalesOrders(ids));
    }



    @Override
    public List<Instant> getOrderDeliveryDatesByShoppingId(Long saleId) {
        var requestResult = requestHandleAndCache(saleId);
        return requestResult.stream()
                .map(RequestOrderDto::getDeliveryDate).collect(Collectors.toList());
    }

    @Override
    public List<RequestOrderDto> getOrdersByShoppingId(Long saleId) {
        return requestHandleAndCache(saleId);
    }

    private List<RequestOrderDto> requestHandleAndCache(Long saleId) {
        final List<Long> ids = new ArrayList<>();
        repository.getSalesOrderById(saleId)
                .ifPresent(so -> ids.addAll(so.getPurchaseOrders()));
        var requestResult = purchaseOrderAdapter.getOrdersInfo(ids).stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        ordersCache.putOrdersInCache(requestResult);
        return requestResult;
    }
}
