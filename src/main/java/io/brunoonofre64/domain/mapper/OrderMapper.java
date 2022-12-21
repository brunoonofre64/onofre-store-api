package io.brunoonofre64.domain.mapper;

import io.brunoonofre64.domain.dto.OrderInputDTO;
import io.brunoonofre64.domain.dto.OrderOutputDTO;
import io.brunoonofre64.domain.entities.OrderEntity;

public interface OrderMapper {

    OrderOutputDTO convertEntityToDTO(OrderEntity entity);

    OrderEntity convertDTOToEntity(OrderInputDTO dto);
}
