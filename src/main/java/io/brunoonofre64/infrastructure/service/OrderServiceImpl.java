package io.brunoonofre64.infrastructure.service;

import io.brunoonofre64.domain.dto.OrderInputDTO;
import io.brunoonofre64.domain.dto.OrderOutputDTO;
import io.brunoonofre64.domain.entities.CustomerEntity;
import io.brunoonofre64.domain.entities.OrderEntity;
import io.brunoonofre64.domain.enums.CodeMessage;
import io.brunoonofre64.domain.exception.DtoNullOrIsEmptyException;
import io.brunoonofre64.domain.exception.ListIsEmptyException;
import io.brunoonofre64.domain.exception.UuidNotFoundOrNullException;
import io.brunoonofre64.domain.mapper.OrderMapper;
import io.brunoonofre64.domain.service.OrderService;
import io.brunoonofre64.infrastructure.jpa.CustomerRepository;
import io.brunoonofre64.infrastructure.jpa.OrderRepository;
import io.brunoonofre64.infrastructure.jpa.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    private CustomerRepository customerRepository;

    private ProductRepository productRepository;

    private OrderMapper mapper;


    @Override
    public OrderEntity saveNewOrderInDb(OrderInputDTO dto) {
       if(validateIfDtoFieldIsNotNullOrEmpty(dto)) {
           throw new DtoNullOrIsEmptyException(CodeMessage.DTO_NULL_OR_IS_EMPTY);
       }
       CustomerEntity customer = getCustomerIfUuidExistInDataBase(dto);

       OrderEntity order = new OrderEntity();
       order.setStatus(dto.getStatus());
       order.setOrderDate(dto.getOrderDate());
       order.setTotal(dto.getTotal());
       order.setCustomer(customer);


      return null;
    }

    @Override
    public OrderOutputDTO getOrderByUuid(String uuid) {
        if(ObjectUtils.isEmpty(uuid) || orderRepository.existsByUuid(uuid)) {
            throw new UuidNotFoundOrNullException((CodeMessage.UUID_NOT_FOUND_OR_NULL));
        }
        OrderEntity entity = orderRepository.findByUuid(uuid);
        return mapper.convertEntityToDTO(entity);
    }

    @Override
    public List<OrderOutputDTO> getAllOrderTheseCustomer() {
        if(ObjectUtils.isEmpty(this)) {
            throw new ListIsEmptyException(CodeMessage.LIST_IS_EMPTY);
        }
        return orderRepository
                .findAll().stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrderOutputDTO updateOrderByUuidAndNewItems(String uuid, OrderInputDTO dto) {
        if(ObjectUtils.isEmpty(uuid) || orderRepository.existsByUuid(uuid)) {
            throw new UuidNotFoundOrNullException((CodeMessage.UUID_NOT_FOUND_OR_NULL));
        }
        if(validateIfDtoFieldIsNotNullOrEmpty(dto)) {
            throw new DtoNullOrIsEmptyException(CodeMessage.DTO_NULL_OR_IS_EMPTY);
        }
        OrderEntity entity = orderRepository.findByUuid(uuid);
        entity.setUuid(dto.getUuid());
        entity.setCustomer(dto.getCustomerEntity());
        entity.setStatus(dto.getStatus());
        entity.setOrderDate(dto.getOrderDate());
        entity.setTotal(dto.getTotal());

        orderRepository.save(entity);

        return mapper.convertEntityToDTO(entity);
    }

    @Override
    @Transactional
    public void deleteOrderByUuid(String uuid) {
        if(ObjectUtils.isEmpty(uuid) || orderRepository.existsByUuid(uuid)) {
            throw new UuidNotFoundOrNullException((CodeMessage.UUID_NOT_FOUND_OR_NULL));
        }
        orderRepository.deleteByUuid(uuid);
    }

    public CustomerEntity getCustomerIfUuidExistInDataBase(OrderInputDTO dto) {
        if(ObjectUtils.isEmpty(dto)) {
            throw new DtoNullOrIsEmptyException(CodeMessage.DTO_NULL_OR_IS_EMPTY);
        }
        String uuidCustomer = dto.getCustomerEntity().getUuid();

        try{
            return customerRepository.findByUuid(uuidCustomer);
        } catch (Exception ex) {
            throw new UuidNotFoundOrNullException(CodeMessage.UUID_NOT_FOUND_OR_NULL);
        }
    }

    private OrderOutputDTO convertEntityToDTO(OrderEntity entity) {
        return mapper.convertEntityToDTO(entity);
    }

    private boolean validateIfDtoFieldIsNotNullOrEmpty(OrderInputDTO dto) {
        return ObjectUtils.isEmpty(dto.getUuid()) && ObjectUtils.isEmpty(dto.getOrderDate())
                && ObjectUtils.isEmpty(dto.getCustomerEntity()) && CollectionUtils.isEmpty(dto.getOrderItems())
                && dto.getCustomerEntity() == null && ObjectUtils.isEmpty(dto.getStatus());
    }
}
