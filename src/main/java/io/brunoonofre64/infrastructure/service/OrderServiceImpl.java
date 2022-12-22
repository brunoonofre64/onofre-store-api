package io.brunoonofre64.infrastructure.service;

import io.brunoonofre64.domain.dto.OrderInputDTO;
import io.brunoonofre64.domain.dto.OrderItemsInputDTO;
import io.brunoonofre64.domain.entities.CustomerEntity;
import io.brunoonofre64.domain.entities.OrderEntity;
import io.brunoonofre64.domain.entities.OrderItemsEntity;
import io.brunoonofre64.domain.entities.ProductEntity;
import io.brunoonofre64.domain.enums.CodeMessage;
import io.brunoonofre64.domain.enums.Status;
import io.brunoonofre64.domain.exception.*;
import io.brunoonofre64.domain.service.OrderService;
import io.brunoonofre64.infrastructure.jpa.CustomerRepository;
import io.brunoonofre64.infrastructure.jpa.OrderItemsRepository;
import io.brunoonofre64.infrastructure.jpa.OrderRepository;
import io.brunoonofre64.infrastructure.jpa.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    private CustomerRepository customerRepository;

    private ProductRepository productRepository;

    private OrderItemsRepository orderItemsRepository;

    @Override
    @Transactional
    public OrderEntity saveNewOrderInDb(OrderInputDTO dto) {
       if(validateIfDtoFieldIsNotNullOrEmpty(dto)) {
           throw new DtoNullOrIsEmptyException(CodeMessage.DTO_NULL_OR_IS_EMPTY);
       }
       CustomerEntity customer = getCustomerIfIdExistInDataBase(dto);

       OrderEntity order = new OrderEntity();
       order.setStatus(Status.APPROVED);
       order.setOrderDate(LocalDateTime.now());
       order.setTotal(dto.getTotal());
       order.setCustomer(customer);

       OrderEntity orderSuccess = orderRepository.save(order);

       List<OrderItemsEntity> orderItems = getOrderItems(orderSuccess, dto.getOrderItems());
       order.setOrderItems(orderItems);

       orderItemsRepository.saveAll(orderItems);

       return order;
    }

    public CustomerEntity getCustomerIfIdExistInDataBase(OrderInputDTO dto) {
        if(ObjectUtils.isEmpty(dto)) {
            throw new DtoNullOrIsEmptyException(CodeMessage.DTO_NULL_OR_IS_EMPTY);
        }
        Long idCustomer = dto.getCustomer();

           return customerRepository.findById(idCustomer)
                   .orElseThrow(() -> new OrderNotFoundException(CodeMessage.ORDER_NOT_FOUND));
    }

    public ProductEntity getProductIfIdExistInDataBase(OrderItemsInputDTO dto) {
        if(ObjectUtils.isEmpty(dto)) {
            throw new UuidNotFoundOrNullException(CodeMessage.DTO_NULL_OR_IS_EMPTY);
        }
        Long idProduct = dto.getProduct();

            return productRepository.findById(idProduct)
                    .orElseThrow(() -> new ProductNotFoundException(CodeMessage.PRODUCT_NOT_FOUND));
    }

    private List<OrderItemsEntity> getOrderItems(OrderEntity order, List<OrderItemsInputDTO> orderitems) {
        if(CollectionUtils.isEmpty(orderitems)) {
            throw new ListIsEmptyException(CodeMessage.ORDER_NOT_FOUND);
        }
        if(order == null) {
            throw new OrderNotFoundException(CodeMessage.ORDER_NOT_FOUND);
        }

        return orderitems
                .stream()
                .map(dto -> {
                    ProductEntity product =  getProductIfIdExistInDataBase(dto);

                    OrderItemsEntity items = new OrderItemsEntity();
                    items.setOrderEntity(order);
                    items.setAmount(dto.getAmount());
                    items.setProduct(product);

                    return items;
                }).collect(Collectors.toList());
    }

    private boolean validateIfDtoFieldIsNotNullOrEmpty(OrderInputDTO dto) {
        return ObjectUtils.isEmpty(dto.getCustomer()) && ObjectUtils.isEmpty(dto.getTotal()) && CollectionUtils.isEmpty(dto.getOrderItems());
    }
}
