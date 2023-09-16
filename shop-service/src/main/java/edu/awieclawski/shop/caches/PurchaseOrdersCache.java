package edu.awieclawski.shop.caches;

import edu.awieclawski.shop.dtos.RequestOrderDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Simple cache map without any management logic
 */
@Slf4j
@Component
public class PurchaseOrdersCache {

    private Map<Long, RequestOrderDto> ordersCache;

    @PostConstruct
    private void initOrders() {
        ordersCache = new HashMap<>();
        log.info("Orders cache initiated");
    }

    public void putOrdersInCache(List<RequestOrderDto> orders) {
        if (!CollectionUtils.isEmpty(orders)) {
            orders.forEach(po -> ordersCache.put(po.getId(), po));
        }
    }

    public Collection<RequestOrderDto> getAllOrders() {
        cacheReady();
        return getAllOrdersMap().values();
    }

    public Optional<RequestOrderDto> getOrderById(Long id) {
        cacheReady();
        return Optional.ofNullable(ordersCache.get(id));
    }

    public Collection<RequestOrderDto> getOrders(List<Long> ids) {
        cacheReady();
        return CollectionUtils.isEmpty(ids) ? new ArrayList<>()
                : ordersCache.values().stream()
                .filter(Objects::nonNull)
                .filter(po -> ids.contains(po.getId()))
                .collect(Collectors.toList());
    }

    private Map<Long, RequestOrderDto> getAllOrdersMap() {
        cacheReady();
        return new HashMap<>(ordersCache);
    }

    private void cacheReady() {
        if (ordersCache == null) {
            initOrders();
        }
    }
}
