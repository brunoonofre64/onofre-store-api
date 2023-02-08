package br.com.onofrestore.infrastructure.mapper;

import br.com.onofrestore.domain.dto.customer.CustomerInformationDTO;
import br.com.onofrestore.domain.dto.order.OrderInformationDTO;
import br.com.onofrestore.domain.dto.order.OrderInputDTO;
import br.com.onofrestore.domain.dto.order.OrderOutputDTO;
import br.com.onofrestore.domain.dto.orderitems.OrderItemsInformationtDTO;
import br.com.onofrestore.domain.entities.CustomerEntity;
import br.com.onofrestore.domain.entities.OrderEntity;
import br.com.onofrestore.domain.entities.OrderItemsEntity;
import br.com.onofrestore.domain.enums.CodeMessage;
import br.com.onofrestore.domain.enums.Status;
import br.com.onofrestore.domain.exception.BusinessRuleException;
import br.com.onofrestore.domain.exception.ListIsEmptyException;
import br.com.onofrestore.domain.mapper.OrderMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderOutputDTO convertEntityToDTO(OrderEntity order, CustomerEntity customer, List<OrderItemsEntity> items) {
        if(order == null || customer == null || CollectionUtils.isEmpty(items)) {
            throw new BusinessRuleException(CodeMessage.OBJECTS_ISNULL_OR_EMPTY);
        }
        CustomerInformationDTO customerDTO =  CustomerInformationDTO
                .builder()
                .name(customer.getName())
                .cpf(customer.getCpf())
                .build();

        return OrderOutputDTO
                .builder()
                .uuid(order.getUuid())
                .orderDate(order.getInclusionDate())
                .status(order.getStatus())
                .total(order.getTotal())
                .customer(customerDTO)
                .build();
    }

    @Override
    public OrderInformationDTO convertOrderItemsToInformationsDTO(OrderEntity orders) {
        if(orders == null) {
            throw new BusinessRuleException(CodeMessage.ENTITY_ISNULL);
        }
       return OrderInformationDTO
               .builder()
               .uuidOrder(orders.getUuid())
               .status(orders.getStatus())
               .orderDate(orders.getInclusionDate())
               .total(orders.getTotal())
               .nameCustomer(orders.getCustomer().getName())
               .cpf(orders.getCustomer().getCpf())
               .orderItems(convertOrdertoDTO(orders.getOrderItems()))
               .build();
    }

    @Override
    public OrderEntity convertDTOAndCustomerToOrderEntity(OrderInputDTO dto, CustomerEntity customer) {
        if(dto == null || customer == null) {
            throw new BusinessRuleException(CodeMessage.OBJECTS_ISNULL_OR_EMPTY);
        }
        return OrderEntity
                .builder()
                .status(Status.APPROVED)
                .total(dto.getTotal())
                .customer(customer)
                .build();
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
