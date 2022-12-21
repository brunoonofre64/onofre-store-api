package io.brunoonofre64.domain.service;

import io.brunoonofre64.domain.dto.OrderInputDTO;
import io.brunoonofre64.domain.dto.OrderOutputDTO;
import io.brunoonofre64.domain.entities.OrderEntity;

import java.util.List;

public interface OrderService {

    OrderEntity saveNewOrderInDb(OrderInputDTO dto);

    OrderOutputDTO getOrderByUuid(String uuid);

    List<OrderOutputDTO> getAllOrderTheseCustomer();

    OrderOutputDTO updateOrderByUuidAndNewItems(String uuid, OrderInputDTO dto);

    void deleteOrderByUuid(String uuid);
}
