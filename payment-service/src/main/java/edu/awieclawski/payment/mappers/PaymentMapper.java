package edu.awieclawski.payment.mappers;

import edu.awieclawski.payment.dtos.PaymentDto;
import edu.awieclawski.payment.dtos.PaymentEventDto;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {

    public PaymentEventDto toEventDto(PaymentDto dto) {
        return dto != null
                ? PaymentEventDto.builder()
                .id(String.valueOf(dto.getId()))
//                .status(dto.getStatus().name())
//                .dueDate(dto.getDueDate())
//                .account(dto.getAccount())
//                .amount(dto.getAmount())
                .subject(dto.getSubject())
                .build()
                : null;
    }

    public PaymentDto toDto(PaymentDto dto) {
        return dto != null
                ? PaymentDto.builder()
                .id(dto.getId())
                .businessEntity(dto.getBusinessEntity())
                .subject(dto.getSubject())
                .account(dto.getAccount())
                .dueDate(dto.getDueDate())
                .amount(dto.getAmount())
                .status(dto.getStatus())
                .build()
                : null;
    }
}
