package io.brunoonofre64.domain.service;

import io.brunoonofre64.domain.dto.DataToCreateOrderDTO;
import io.brunoonofre64.domain.dto.OrderDTO;

import java.util.List;

public interface OrderService {

    OrderDTO saveNewOrderInDb(DataToCreateOrderDTO dto);

    OrderDTO getOrderByUuid(String uuid);

    List<OrderDTO> getAllOrderTheseCustomer();

    OrderDTO updateOrderByUuidAndNewItems(String uuid, DataToCreateOrderDTO dto);

    void deleteOrderByUuid(String uuid);
}
