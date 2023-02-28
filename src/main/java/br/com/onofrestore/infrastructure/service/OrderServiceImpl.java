package br.com.onofrestore.infrastructure.service;

import br.com.onofrestore.domain.dto.order.OrderInformationDTO;
import br.com.onofrestore.domain.dto.order.OrderNewStatusDTO;
import br.com.onofrestore.domain.dto.order.OrderOutputDTO;
import br.com.onofrestore.domain.dto.orderitems.OrderItemsInputDTO;
import br.com.onofrestore.domain.entities.OrderEntity;
import br.com.onofrestore.domain.entities.OrderItemsEntity;
import br.com.onofrestore.domain.entities.ProductEntity;
import br.com.onofrestore.domain.entities.UserEntity;
import br.com.onofrestore.domain.enums.CodeMessage;
import br.com.onofrestore.domain.exception.*;
import br.com.onofrestore.domain.mapper.OrderMapper;
import br.com.onofrestore.domain.service.OrderService;
import br.com.onofrestore.infrastructure.config.security.OnofreSecurity;
import br.com.onofrestore.infrastructure.jpa.repositories.OrderItemsRepository;
import br.com.onofrestore.infrastructure.jpa.repositories.OrderRepository;
import br.com.onofrestore.infrastructure.jpa.repositories.ProductRepository;
import br.com.onofrestore.infrastructure.jpa.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.CollectionUtils.isEmpty;
import static org.springframework.util.ObjectUtils.isEmpty;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private UserRepository userRepository;
    private ProductRepository productRepository;
    private OrderItemsRepository orderItemsRepository;
    private OnofreSecurity onofreSecurity;
    private OrderMapper orderMapper;

    @Override
    @Transactional
    public OrderOutputDTO saveNewOrderInDb(List<OrderItemsInputDTO> dto) {
        if (CollectionUtils.isEmpty(dto)) {
            throw new ListIsEmptyException(CodeMessage.ORDER_NOT_FOUND);
        }

        UserEntity user = this.getUserByUuidInJwt();

        if (user == null) {
            throw new UserNotFoundException(CodeMessage.USER_NOTFOUND);
        }
        OrderEntity order = orderMapper.convertItemsAndUserToOrder(dto, user);

        List<OrderItemsEntity> orderItems = this.getOrderItems(order, dto);
        order.setOrderItems(orderItems);

        BigDecimal totalOrder = this.calculateOrderTotal(orderItems);
        order.setTotal(totalOrder);

        orderRepository.save(order);
        orderItemsRepository.saveAll(orderItems);

        return orderMapper.convertEntityToDTO(order, user, orderItems);
    }

    @Override
    public OrderOutputDTO getOrderByUuid(String uuid) {
        OrderEntity order = orderRepository.findByUuid(uuid);

        if (order == null) {
            throw new OrderNotFoundException(CodeMessage.ORDER_NOT_FOUND);
        }

        UserEntity user = this.getUserByUuidInJwt();

        if (user == null) {
            throw new UserNotFoundException(CodeMessage.USER_NOTFOUND);
        }
        return orderMapper.convertOrderToDTO(order, user);
    }

    @Override
    public Page<OrderInformationDTO> getAllOrderPaged() {
        UserEntity user = this.getUserByUuidInJwt();

        if (user == null) {
            throw new UserNotFoundException(CodeMessage.USER_NOTFOUND);
        }

        Page<OrderInformationDTO> orderPaged = orderMapper.convertToPageDTO(user.getOrders());

        if (orderPaged.isEmpty()) {
            throw new OrderNotFoundException(CodeMessage.ORDER_NOT_FOUND);
        } else {
            return orderPaged;
        }
    }

    @Override
    public OrderInformationDTO updateStatusOrderByUuid(String uuid, OrderNewStatusDTO dto) {
        if (isEmpty(dto.getStatus())) {
            throw new DtoNullOrIsEmptyException(CodeMessage.DTO_NULL_OR_IS_EMPTY);
        }

        OrderEntity order = orderRepository.findByUuid(uuid);

        if (order == null) {
            throw new OrderNotFoundException(CodeMessage.ORDER_NOT_FOUND);
        } else {
            order.setStatus(dto.getStatus());

            orderRepository.save(order);
            return orderMapper.convertOrderItemsToInformationsDTO(order);
        }
    }

    @Transactional
    public void deleteOrderByUuid(String uuid) {
        this.validateOrderUuid(uuid);

        orderRepository.deleteByUuid(uuid);
    }

    public ProductEntity getProductInDatabase(OrderItemsInputDTO dto) {
        if (isEmpty(dto)) {
            throw new UuidNotFoundOrNullException(CodeMessage.DTO_NULL_OR_IS_EMPTY);
        }

        String uuidProduct = dto.getUuidProduct();

        try {
            return productRepository.findByUuid(uuidProduct);
        } catch (Exception ex) {
            throw new ProductNotFoundException(CodeMessage.PRODUCT_NOT_FOUND);
        }
    }

    private void validateOrderUuid(String uuid) {
        if (isEmpty(uuid) || !orderRepository.existsByUuid(uuid)) {
            throw new OrderNotFoundException(CodeMessage.ORDER_NOT_FOUND);
        }
    }

    private List<OrderItemsEntity> getOrderItems(OrderEntity order, List<OrderItemsInputDTO> orderitems) {
        if (isEmpty(orderitems)) {
            throw new ListIsEmptyException(CodeMessage.ORDER_NOT_FOUND);
        }
        if (order == null) {
            throw new OrderNotFoundException(CodeMessage.ORDER_NOT_FOUND);
        }

        return orderitems.stream().map(dto -> {
            ProductEntity product = this.getProductInDatabase(dto);

            return OrderItemsEntity
                    .builder()
                    .orderEntity(order)
                    .amount(dto.getAmount())
                    .product(product).build();
        }).collect(Collectors.toList());
    }

    private BigDecimal calculateOrderTotal(List<OrderItemsEntity> orderItems) {
        if (CollectionUtils.isEmpty(orderItems)) {
            throw new ListIsEmptyException((CodeMessage.LIST_IS_EMPTY));
        }

        return orderItems
                .stream()
                .map(item -> item.getProduct().getUnitValue().multiply(
                        BigDecimal.valueOf(item.getAmount())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private UserEntity getUserByUuidInJwt() {
        String userUuid = onofreSecurity.getUserUuid();
        return userRepository.findByUuid(userUuid);
    }
}
