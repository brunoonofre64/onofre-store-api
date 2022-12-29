package io.brunoonofre64.domain.mapper;

import io.brunoonofre64.domain.dto.OrderInputDTO;
import io.brunoonofre64.domain.dto.OrderInformationDTO;
import io.brunoonofre64.domain.dto.OrderOutputDTO;
import io.brunoonofre64.domain.entities.CustomerEntity;
import io.brunoonofre64.domain.entities.OrderEntity;
import io.brunoonofre64.domain.entities.OrderItemsEntity;

import java.util.List;

public interface OrderMapper {

    OrderOutputDTO convertEntityToDTO(OrderEntity order, CustomerEntity customer, List<OrderItemsEntity> items);

    OrderEntity convertDTOAndCustomerToOrderEntity(OrderInputDTO dto, CustomerEntity customer);

    OrderInformationDTO convertOrderItemsToInformationsDTO(OrderEntity orders);
}
