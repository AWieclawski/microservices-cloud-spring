package edu.awieclawski.order.repositories;

import edu.awieclawski.order.dtos.PurchaseOrderDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Training Repository to simulate database results
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final Random randomGenerator = new Random(); // simulates database id generator

    private Map<Long, PurchaseOrderDto> ordersCache;

    @PostConstruct
    private void initOrders() {
        Long id = 1L;
        Map<Long, PurchaseOrderDto> mockOrderData = new HashMap<>();
        mockOrderData.put(id, new PurchaseOrderDto(id++, "Steel Delivery & Co.", "Shapes 10LAG", 1103, "Chicago 32478, St.Elms ave 231/4", new BigDecimal("1234.56"), Instant.now().minus(2, ChronoUnit.DAYS), Instant.now().plus(2, ChronoUnit.DAYS), List.of(1L)));
        mockOrderData.put(id, new PurchaseOrderDto(id++, "Ferry Factory & Co.", "Steel plates 11/C", 234, "New York 65421, Washington str 12", new BigDecimal("3254.34"), Instant.now().minus(2, ChronoUnit.DAYS), Instant.now().plus(3, ChronoUnit.DAYS), List.of(2L)));
        mockOrderData.put(id, new PurchaseOrderDto(id++, "Foundry Industrial JSCo", "Wire scroll 5G/450", 347, "Washington 87901, Bush ave 32/4", new BigDecimal("2345.78"), Instant.now().minus(2, ChronoUnit.DAYS), Instant.now().plus(1, ChronoUnit.DAYS), List.of(3L, 4L)));
        mockOrderData.put(id, new PurchaseOrderDto(id++, "BillY Wholesaler & Co.", "Rod 16CC/430", 152, "San Francisco 43876, Kosciusko 11A", new BigDecimal("3456.87"), Instant.now().minus(2, ChronoUnit.DAYS), Instant.now().plus(4, ChronoUnit.DAYS), List.of(6L)));
        mockOrderData.put(id, new PurchaseOrderDto(id++, "Alloys&Shapes Ind.", "ALU BGC 123B/32", 176, "New Orleans 23091, Lime str 45/1", new BigDecimal("4534.89"), Instant.now().minus(2, ChronoUnit.DAYS), Instant.now().plus(1, ChronoUnit.DAYS), List.of(7L)));
        mockOrderData.put(id, new PurchaseOrderDto(id++, "Elisabeth Queen Stock JSCo", "Column QR55/240", 372, "Los Angeles 65312, Presley ave 111", new BigDecimal("4321.98"), Instant.now().minus(2, ChronoUnit.DAYS), Instant.now().plus(2, ChronoUnit.DAYS), List.of(9L)));
        mockOrderData.put(id, new PurchaseOrderDto(id++, "Martha Ferry Trade JSCo", "Pipe 2IN/220", 89, "Boston 75321, Church str 23/4", new BigDecimal("7654.21"), Instant.now().minus(2, ChronoUnit.DAYS), Instant.now().plus(3, ChronoUnit.DAYS), List.of(10L)));
        mockOrderData.put(id, new PurchaseOrderDto(id++, "JBG Co.", "T-Shape 321/E", 541, "Boston 75321, Church str 23/4", new BigDecimal("7654.21"), Instant.now().minus(2, ChronoUnit.DAYS), Instant.now().plus(5, ChronoUnit.DAYS), List.of(11L)));
        ordersCache = mockOrderData;
        log.info("Orders loaded {}", ordersCache.size());
    }

    public Collection<PurchaseOrderDto> getAllOrders() {
        cacheReady();
        return getAllOrdersMap().values();
    }

    public Optional<PurchaseOrderDto> getOrderById(Long id) {
        cacheReady();
        return Optional.ofNullable(ordersCache.get(id));
    }

    public Collection<PurchaseOrderDto> getOrders(List<Long> ids) {
        cacheReady();
        return CollectionUtils.isEmpty(ids) ? new ArrayList<>()
                : ordersCache.values().stream()
                .filter(Objects::nonNull)
                .filter(po -> ids.contains(po.getId()))
                .collect(Collectors.toList());
    }

    public Long generateId(){
        return randomGenerator.nextLong();
    }

    private Map<Long, PurchaseOrderDto> getAllOrdersMap() {
        cacheReady();
        return new HashMap<>(ordersCache);
    }

    private void cacheReady() {
        if (ordersCache == null) {
            initOrders();
        }
    }

}
