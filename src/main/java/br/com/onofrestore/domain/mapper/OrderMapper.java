package br.com.onofrestore.domain.mapper;

import br.com.onofrestore.domain.entities.CustomerEntity;
import br.com.onofrestore.domain.entities.OrderEntity;
import br.com.onofrestore.domain.entities.OrderItemsEntity;
import br.com.onofrestore.domain.dto.order.OrderInputDTO;
import br.com.onofrestore.domain.dto.order.OrderInformationDTO;
import br.com.onofrestore.domain.dto.order.OrderOutputDTO;

import java.util.List;

public interface OrderMapper {

    OrderOutputDTO convertEntityToDTO(OrderEntity order, CustomerEntity customer, List<OrderItemsEntity> items);

    OrderEntity convertDTOAndCustomerToOrderEntity(OrderInputDTO dto, CustomerEntity customer);

    OrderInformationDTO convertOrderItemsToInformationsDTO(OrderEntity orders);
}
