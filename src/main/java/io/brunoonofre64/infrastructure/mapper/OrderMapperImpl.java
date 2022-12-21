package io.brunoonofre64.infrastructure.mapper;

import io.brunoonofre64.domain.dto.OrderInputDTO;
import io.brunoonofre64.domain.dto.OrderOutputDTO;
import io.brunoonofre64.domain.entities.OrderEntity;
import io.brunoonofre64.domain.mapper.OrderMapper;
import org.springframework.stereotype.Component;

@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderOutputDTO convertEntityToDTO(OrderEntity orderEntity) {
        OrderOutputDTO dto = new OrderOutputDTO();

        dto.setUuid(orderEntity.getUuid());
        dto.setCustomerEntity(orderEntity.getCustomer());
        dto.setOrderDate(orderEntity.getOrderDate());
        dto.setTotal(orderEntity.getTotal());
        dto.setStatus(orderEntity.getStatus());

        return dto;
    }

    @Override
    public OrderEntity convertDTOToEntity(OrderInputDTO dto) {
        OrderEntity entity = new OrderEntity();

        entity.setUuid(dto.getUuid());
        entity.setCustomer(dto.getCustomerEntity());
        entity.setOrderDate(dto.getOrderDate());
        entity.setTotal(dto.getTotal());
        entity.setStatus(dto.getStatus());

        return entity;
    }
}
