package edu.awieclawski.shop.services;

import edu.awieclawski.shop.dtos.RequestOrderDto;
import edu.awieclawski.shop.dtos.SalesOrderDto;

import java.time.Instant;
import java.util.List;

public interface SalesOrderService {

    SalesOrderDto getSalesOrder(Long id);

    List<SalesOrderDto> getSalesOrders(List<Long> ids);

    List<Instant> getOrderDeliveryDatesByShoppingId(Long saleId);

    List<RequestOrderDto> getOrdersByShoppingId(Long saleId);

}
