package edu.awieclawski.payment.repositories;

import edu.awieclawski.payment.dtos.PaymentDto;
import edu.awieclawski.payment.enums.AssetSide;
import edu.awieclawski.payment.enums.PaymentStatus;
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
public class PaymentRepository {

    private final Random randomGenerator = new Random(); // simulates database id generator

    private Map<Long, PaymentDto> paymentsCache;

    @PostConstruct
    private void initPayments() {
        Long id = 0L;
        Map<Long, PaymentDto> mockOrderData = new HashMap<>();
        mockOrderData.put(id, new PaymentDto(++id, "Steel Delivery & Co.", "Shapes 10LAG", new BigDecimal("1234.56"), Instant.now().plus(12, ChronoUnit.DAYS), "IE12BOFI90000112345678", PaymentStatus.PENDING, AssetSide.COMMITMENT));
        mockOrderData.put(id, new PaymentDto(++id, "Ferry Factory & Co.", "Steel plates 11/C", new BigDecimal("3254.34"), Instant.now().plus(13, ChronoUnit.DAYS), "IE12BOFI90000112345321", PaymentStatus.STARTED, AssetSide.COMMITMENT));
        mockOrderData.put(id, new PaymentDto(++id, "Foundry Industrial JSCo", "Wire scroll 5G/450", new BigDecimal("2345.78"), Instant.now().plus(11, ChronoUnit.DAYS), "IE12BOFI90000112347389", PaymentStatus.CANCELLED, AssetSide.COMMITMENT));
        mockOrderData.put(id, new PaymentDto(++id, "Foundry Industrial JSCo", "Wire scroll 5G/450", new BigDecimal("1345.78"), Instant.now().plus(10, ChronoUnit.DAYS), "IE12BOFI90000112347389", PaymentStatus.STARTED, AssetSide.COMMITMENT));
        mockOrderData.put(id, new PaymentDto(++id, "USA-WDC-321", "197-08-2023", new BigDecimal("77654.21"), Instant.now().plus(3, ChronoUnit.DAYS), null, PaymentStatus.STARTED, AssetSide.RECEIVABLE));
        mockOrderData.put(id, new PaymentDto(++id, "BillY Wholesaler & Co.", "Rod 16CC/430", new BigDecimal("3456.87"), Instant.now().plus(14, ChronoUnit.DAYS), "IE12BOFI90000112303456", PaymentStatus.PENDING, AssetSide.COMMITMENT));
        mockOrderData.put(id, new PaymentDto(++id, "Alloys&Shapes Ind.", "ALU BGC 123B/32", new BigDecimal("4534.89"), Instant.now().plus(11, ChronoUnit.DAYS), "IE12BOFI90000112343821", PaymentStatus.STARTED, AssetSide.COMMITMENT));
        mockOrderData.put(id, new PaymentDto(++id, "USA-CHI-356", "231-09-2023", new BigDecimal("6456.87"), Instant.now().plus(4, ChronoUnit.DAYS), null, PaymentStatus.PENDING, AssetSide.RECEIVABLE));
        mockOrderData.put(id, new PaymentDto(++id, "Elisabeth Queen Stock JSCo", "Column QR55/240", new BigDecimal("4321.98"), Instant.now().plus(12, ChronoUnit.DAYS), "IE12BOFI90000112349327", PaymentStatus.PENDING, AssetSide.COMMITMENT));
        mockOrderData.put(id, new PaymentDto(++id, "Martha Ferry Trade JSCo", "Pipe 2IN/220", new BigDecimal("7654.21"), Instant.now().plus(3, ChronoUnit.DAYS), "IE12BOFI90000112306427", PaymentStatus.STARTED, AssetSide.COMMITMENT));
        mockOrderData.put(id, new PaymentDto(++id, "JBG Co.", "T-Shape 321/E", new BigDecimal("4634.21"), Instant.now().plus(5, ChronoUnit.DAYS), "IE12BOFI90000112342804", PaymentStatus.PENDING, AssetSide.COMMITMENT));
        mockOrderData.put(id, new PaymentDto(++id, "USA-SFC-973", "258-09-2023", new BigDecimal("14654.43"), Instant.now().plus(1, ChronoUnit.DAYS), null, PaymentStatus.PENDING, AssetSide.RECEIVABLE));
        mockOrderData.put(id, new PaymentDto(++id, "ABS Inc.", "WL-Shape 100/E", new BigDecimal("5654.34"), Instant.now().minus(5, ChronoUnit.DAYS), "IE12BOFI9000011231242", PaymentStatus.PAID, AssetSide.COMMITMENT));
        paymentsCache = mockOrderData;
        log.info("Payments loaded {}", paymentsCache.size());
    }

    public Collection<PaymentDto> getAllPayments() {
        cacheReady();
        return getAllPaymentsMap().values();
    }

    public Optional<PaymentDto> getPaymentById(Long id) {
        cacheReady();
        return Optional.ofNullable(paymentsCache.get(id));
    }

    public Collection<PaymentDto> getPayments(List<Long> ids) {
        cacheReady();
        return CollectionUtils.isEmpty(ids) ? new ArrayList<>()
                : paymentsCache.values().stream()
                .filter(Objects::nonNull)
                .filter(po -> ids.contains(po.getId()))
                .collect(Collectors.toList());
    }

    public Long generateId() {
        var id = randomGenerator.nextLong();
        if (id > Long.MIN_VALUE) {
            return Math.abs(id);
        }
        return Math.abs(id + 1L);
    }

    private Map<Long, PaymentDto> getAllPaymentsMap() {
        cacheReady();
        return new HashMap<>(paymentsCache);
    }

    private void cacheReady() {
        if (paymentsCache == null) {
            initPayments();
        }
    }

}
