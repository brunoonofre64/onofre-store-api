package io.brunoonofre64.infrastructure.mapper;

import io.brunoonofre64.domain.dto.*;
import io.brunoonofre64.domain.entities.CustomerEntity;
import io.brunoonofre64.domain.entities.OrderEntity;
import io.brunoonofre64.domain.entities.OrderItemsEntity;
import io.brunoonofre64.domain.enums.CodeMessage;
import io.brunoonofre64.domain.enums.Status;
import io.brunoonofre64.domain.exception.ListIsEmptyException;
import io.brunoonofre64.domain.mapper.OrderMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

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
    public OrderInformationDTO convertOrderItemsToInformationsDTO(OrderEntity orders) {
       return OrderInformationDTO
               .builder()
               .uuidOrder(orders.getUuid())
               .status(orders.getStatus())
               .orderDate(orders.getOrderDate())
               .total(orders.getTotal())
               .nameCustomer(orders.getCustomer().getName())
               .cpf(orders.getCustomer().getCpf())
               .orderItems(convertOrdertoDTO(orders.getOrderItems()))
               .build();
    }

    @Override
    public OrderEntity convertDTOAndCustomerToOrderEntity(OrderInputDTO dto, CustomerEntity customer) {
        OrderEntity order = new OrderEntity();

        order.setStatus(Status.APPROVED);
        order.setTotal(dto.getTotal());
        order.setCustomer(customer);

        return order;
    }

    private List<OrderItemsInformationtDTO> convertOrdertoDTO(List<OrderItemsEntity> items) {
        if(CollectionUtils.isEmpty(items)) {
            throw new ListIsEmptyException(CodeMessage.LIST_IS_EMPTY);
        }
        return items
                .stream()
                .map( itemsInfo ->
                        OrderItemsInformationtDTO
                                .builder()
                                .productName(itemsInfo.getProduct().getProductName())
                                .description(itemsInfo.getProduct().getDescription())
                                .unitValue(itemsInfo.getProduct().getUnitValue())
                                .amount(itemsInfo.getAmount())
                                .build()
                ).collect(Collectors.toList());
    }
}
