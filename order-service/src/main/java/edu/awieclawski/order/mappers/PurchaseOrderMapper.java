package edu.awieclawski.order.mappers;

import edu.awieclawski.order.dtos.PurchaseOrderDto;
import edu.awieclawski.order.dtos.ResponseOrderDto;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Component
public class PurchaseOrderMapper {

    public ResponseOrderDto optionalToSharedDto(PurchaseOrderDto po) {
        return po != null ? ResponseOrderDto.builder()
                .subject(po.getSubject())
                .quantity(po.getQuantity())
                .deliveryDate(po.getDeliveryDate())
                .id(po.getId())
                .build()
                : null;
    }

    public ResponseOrderDto optionalToSharedDto(Optional<PurchaseOrderDto> optional) {
        return optional.map(dto -> ResponseOrderDto.builder()
                .subject(dto.getSubject())
                .quantity(dto.getQuantity())
                .deliveryDate(dto.getDeliveryDate())
                .id(dto.getId())
                .build()).orElse(null);
    }

    public List<ResponseOrderDto> toSharedDtos(Collection<PurchaseOrderDto> pos) {
        return CollectionUtils.isEmpty(pos)
                ? new ArrayList<>()
                : pos.stream()
                .map(this::optionalToSharedDto)
                .collect(Collectors.toList());
    }
}
