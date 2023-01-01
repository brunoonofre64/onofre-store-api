package io.brunoonofre64.domain.service;

import io.brunoonofre64.domain.dto.order.OrderInputDTO;
import io.brunoonofre64.domain.dto.order.OrderInformationDTO;
import io.brunoonofre64.domain.dto.order.OrderNewStatusDTO;
import io.brunoonofre64.domain.dto.order.OrderOutputDTO;

public interface OrderService {

    OrderOutputDTO saveNewOrderInDb(OrderInputDTO dto);

    OrderInformationDTO getOrderItemsInformationByUuid(String uuid);

    void cancelOrderByUuid(String uuid);

    OrderInformationDTO updateStatusOfOrderByUuid(String uuid, OrderNewStatusDTO dto);
}
