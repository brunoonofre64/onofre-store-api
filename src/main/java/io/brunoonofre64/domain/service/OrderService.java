package io.brunoonofre64.domain.service;

import io.brunoonofre64.domain.dto.OrderInputDTO;
import io.brunoonofre64.domain.entities.OrderEntity;

public interface OrderService {

    OrderEntity saveNewOrderInDb(OrderInputDTO dto);
}
