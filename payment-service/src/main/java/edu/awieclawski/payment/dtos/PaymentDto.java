package edu.awieclawski.payment.dtos;

import edu.awieclawski.payment.enums.AssetSide;
import edu.awieclawski.payment.enums.PaymentStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

//@Value
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class PaymentDto {
    Long id;
    String businessEntity;
    String subject;
    BigDecimal amount;
    Instant dueDate;
    String account;
    PaymentStatus status;
    AssetSide side;

    public PaymentDto(Long id, String businessEntity, String subject) {
        this.id = id;
        this.businessEntity = businessEntity;
        this.subject = subject;
    }
}
