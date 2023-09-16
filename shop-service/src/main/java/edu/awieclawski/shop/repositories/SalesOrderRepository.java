package edu.awieclawski.shop.repositories;

import edu.awieclawski.shop.dtos.SalesOrderDto;
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
@Repository
@Slf4j
public class SalesOrderRepository {

    private final Random randomGenerator = new Random(); // simulates database id generator

    private Map<Long, SalesOrderDto> shoppingCache;

    @PostConstruct
    private void initOrders() {
        Long id = 1L;
        Map<Long, SalesOrderDto> mockShopping = new HashMap<>();
        mockShopping.put(id, new SalesOrderDto(id++, "USA-WDC-321", "197-08-2023", new BigDecimal("77654.21"), Instant.now().minus(12, ChronoUnit.DAYS), List.of(1L, 2L, 3L), List.of(5L)));
        mockShopping.put(id, new SalesOrderDto(id++, "USA-CHI-356", "231-09-2023", new BigDecimal("66531.43"), Instant.now().minus(14, ChronoUnit.DAYS), List.of(4L, 5L), List.of(8L)));
        mockShopping.put(id, new SalesOrderDto(id++, "USA-SFC-973", "258-09-2023", new BigDecimal("14654.43"), Instant.now().minus(15, ChronoUnit.DAYS), List.of(6L, 7L, 8L), List.of(12L)));
        shoppingCache = mockShopping;
        log.info("Sales Orders loaded {}", shoppingCache.size());
    }


    public Optional<SalesOrderDto> getSalesOrderById(Long id) {
        return Optional.ofNullable(getAllShopping().get(id));
    }

    public Collection<SalesOrderDto> getSalesOrders(List<Long> ids) {
        return CollectionUtils.isEmpty(ids) ? new ArrayList<>()
                : getAllShopping().values().stream()
                .filter(Objects::nonNull)
                .filter(so -> ids.contains(so.getId()))
                .collect(Collectors.toList());
    }

    public Long generateId() {
        return randomGenerator.nextLong();
    }

    private Map<Long, SalesOrderDto> getAllShopping() {
        if (shoppingCache == null) {
            initOrders();
        }
        return shoppingCache;
    }

}
