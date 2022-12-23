package io.brunoonofre64.domain.service;

import io.brunoonofre64.domain.dto.OrderInputDTO;
import io.brunoonofre64.domain.dto.OrderOutputDTO;

public interface OrderService {

    OrderOutputDTO saveNewOrderInDb(OrderInputDTO dto);
}
