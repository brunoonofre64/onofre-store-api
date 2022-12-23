package io.brunoonofre64.infrastructure.mapper;

import io.brunoonofre64.domain.dto.CustomerInformationDTO;
import io.brunoonofre64.domain.dto.OrderInputDTO;
import io.brunoonofre64.domain.dto.OrderOutputDTO;
import io.brunoonofre64.domain.entities.CustomerEntity;
import io.brunoonofre64.domain.entities.OrderEntity;
import io.brunoonofre64.domain.entities.OrderItemsEntity;
import io.brunoonofre64.domain.enums.Status;
import io.brunoonofre64.domain.mapper.OrderMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderOutputDTO convertEntityToDTO(OrderEntity order, CustomerEntity customer, List<OrderItemsEntity> items) {
        CustomerInformationDTO customerDTO = new CustomerInformationDTO();
        customerDTO.setName(customer.getName());
        customerDTO.setCpf(customer.getCpf());

        OrderOutputDTO dto = new OrderOutputDTO();

        dto.setUuid(order.getUuid());
        dto.setOrderDate(order.getOrderDate());
        dto.setStatus(order.getStatus());
        dto.setTotal(order.getTotal());
        dto.setCustomer(customerDTO);

        return dto;
    }

    @Override
    public OrderEntity convertDTOAndCustomerToOrderEntity(OrderInputDTO dto, CustomerEntity customer) {
        OrderEntity order = new OrderEntity();

        order.setStatus(Status.APPROVED);
        order.setTotal(dto.getTotal());
        order.setCustomer(customer);

        return order;
    }
}
