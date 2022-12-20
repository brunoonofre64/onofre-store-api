package io.brunoonofre64.domain.mapper;

import io.brunoonofre64.domain.dto.DataToCreateOrderDTO;
import io.brunoonofre64.domain.dto.OrderDTO;
import io.brunoonofre64.domain.entities.OrderEntity;

public interface OrderMapper {

    OrderDTO convertEntityToDTO(OrderEntity entity);

    OrderEntity convertDTOToEntity(DataToCreateOrderDTO dto);
}
