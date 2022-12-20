package io.brunoonofre64.infrastructure.mapper;

import io.brunoonofre64.domain.dto.DataToCreateOrderDTO;
import io.brunoonofre64.domain.dto.OrderDTO;
import io.brunoonofre64.domain.entities.OrderEntity;
import io.brunoonofre64.domain.mapper.OrderMapper;
import org.springframework.stereotype.Component;

@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderDTO convertEntityToDTO(OrderEntity orderEntity) {
        OrderDTO dto = new OrderDTO();

        dto.setUuid(orderEntity.getUuid());
        dto.setCustomerEntity(orderEntity.getCustomerEntity());
        dto.setOrderDate(orderEntity.getOrderDate());
        dto.setTotal(orderEntity.getTotal());
        dto.setStatus(orderEntity.getStatus());

        return dto;
    }

    @Override
    public OrderEntity convertDTOToEntity(DataToCreateOrderDTO dto) {
        OrderEntity entity = new OrderEntity();

        entity.setUuid(dto.getUuid());
        entity.setCustomerEntity(dto.getCustomerEntity());
        entity.setOrderDate(dto.getOrderDate());
        entity.setTotal(dto.getTotal());
        entity.setStatus(dto.getStatus());

        return entity;
    }
}
