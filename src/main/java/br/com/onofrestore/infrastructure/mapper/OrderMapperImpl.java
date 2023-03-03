package br.com.onofrestore.infrastructure.mapper;

import br.com.onofrestore.domain.dto.user.UserInfoDTO;
import br.com.onofrestore.domain.dto.order.OrderInformationDTO;
import br.com.onofrestore.domain.dto.order.OrderOutputDTO;
import br.com.onofrestore.domain.dto.orderitems.OrderItemsInformationtDTO;
import br.com.onofrestore.domain.dto.orderitems.OrderItemsInputDTO;
import br.com.onofrestore.domain.entities.OrderEntity;
import br.com.onofrestore.domain.entities.OrderItemsEntity;
import br.com.onofrestore.domain.entities.UserEntity;
import br.com.onofrestore.domain.enums.CodeMessage;
import br.com.onofrestore.domain.enums.Status;
import br.com.onofrestore.domain.exception.BusinessRuleException;
import br.com.onofrestore.domain.exception.ListIsEmptyException;
import br.com.onofrestore.domain.mapper.OrderMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.CollectionUtils.isEmpty;

@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderOutputDTO convertEntityToDTO(OrderEntity order, UserEntity user, List<OrderItemsEntity> items) {
        if (order == null || user == null || isEmpty(items)) {
            throw new BusinessRuleException(CodeMessage.OBJECTS_ISNULL_OR_EMPTY);
        }
        return this.convertOrderToDTO(order, user);
    }

    @Override
    public OrderOutputDTO convertOrderToDTO(OrderEntity order, UserEntity user) {
        UserInfoDTO userInfoDTO = UserInfoDTO
                .builder()
                .email(user.getEmail())
                .fullName(user.getFullName())
                .userUuid(user.getUuid())
                .build();

        return OrderOutputDTO
                .builder()
                .uuid(order.getUuid())
                .orderDate(order.getInclusionDate())
                .status(order.getStatus())
                .total(order.getTotal())
                .userInfoDTO(userInfoDTO)
                .build();
    }

    @Override
    public Page<OrderInformationDTO> convertToPageDTO(List<OrderEntity> entity) {
        if (entity.isEmpty()) {
            throw new ListIsEmptyException(CodeMessage.LIST_IS_EMPTY);
        }
        List<OrderInformationDTO> infoDTO = entity
                .stream()
                .map(this::convertOrderItemsToInformationsDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(infoDTO);
    }

    @Override
    public OrderInformationDTO convertOrderItemsToInformationsDTO(OrderEntity orders) {
        if (orders == null) {
            throw new BusinessRuleException(CodeMessage.ENTITY_ISNULL);
        }
        return OrderInformationDTO
                .builder()
                .uuidOrder(orders.getUuid())
                .status(orders.getStatus())
                .orderDate(orders.getInclusionDate())
                .total(orders.getTotal())
                .nameUser(orders.getUsers().getFullName())
                .cpf(orders.getUsers().getCpf())
                .orderItems(convertOrderItemsToDTO(orders.getOrderItems()))
                .build();
    }

    @Override
    public OrderEntity convertItemsAndUserToOrder(List<OrderItemsInputDTO> orderItems, UserEntity user) {
        if (CollectionUtils.isEmpty(orderItems) || user == null) {
            throw new BusinessRuleException(CodeMessage.OBJECTS_ISNULL_OR_EMPTY);
        }
        return OrderEntity
                .builder()
                .status(Status.APPROVED)
                .users(user)
                .build();
    }

    private List<OrderItemsInformationtDTO> convertOrderItemsToDTO(List<OrderItemsEntity> items) {
        if (isEmpty(items)) {
            throw new ListIsEmptyException(CodeMessage.LIST_IS_EMPTY);
        }
        return items
                .stream()
                .map(itemsInfo ->
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
