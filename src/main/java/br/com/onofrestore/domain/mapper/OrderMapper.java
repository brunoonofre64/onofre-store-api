package br.com.onofrestore.domain.mapper;

import br.com.onofrestore.domain.entities.OrderEntity;
import br.com.onofrestore.domain.entities.OrderItemsEntity;
import br.com.onofrestore.domain.dto.order.OrderInputDTO;
import br.com.onofrestore.domain.dto.order.OrderInformationDTO;
import br.com.onofrestore.domain.dto.order.OrderOutputDTO;
import br.com.onofrestore.domain.entities.UserEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderMapper {

    OrderOutputDTO convertEntityToDTO(OrderEntity order, UserEntity user, List<OrderItemsEntity> items);
    Page<OrderInformationDTO> convertToPageDTO(List<OrderEntity> entity);

    OrderEntity convertDTOAndCustomerToOrderEntity(OrderInputDTO dto, UserEntity user);

    OrderInformationDTO convertOrderItemsToInformationsDTO(OrderEntity orders);
}
