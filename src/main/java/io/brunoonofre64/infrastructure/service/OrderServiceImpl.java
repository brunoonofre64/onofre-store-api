package io.brunoonofre64.infrastructure.service;

import io.brunoonofre64.domain.dto.DataToCreateOrderDTO;
import io.brunoonofre64.domain.dto.OrderDTO;
import io.brunoonofre64.domain.entities.OrderEntity;
import io.brunoonofre64.domain.enums.CodeMessage;
import io.brunoonofre64.domain.exception.DtoNullOrIsEmptyException;
import io.brunoonofre64.domain.exception.ListIsEmptyException;
import io.brunoonofre64.domain.exception.UuidNotFoundOrNullException;
import io.brunoonofre64.domain.mapper.OrderMapper;
import io.brunoonofre64.domain.service.OrderService;
import io.brunoonofre64.infrastructure.jpa.OrderRepository;
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

    private OrderRepository repository;

    private OrderMapper mapper;


    @Override
    public OrderDTO saveNewOrderInDb(DataToCreateOrderDTO dto) {
       if(validateIfDtoFieldIsNotNullOrEmpty(dto)) {
           throw new DtoNullOrIsEmptyException(CodeMessage.DTO_NULL_OR_IS_EMPTY);
       }
       OrderEntity orderEntity = mapper.convertDTOToEntity(dto);
       repository.save(orderEntity);

       return mapper.convertEntityToDTO(orderEntity);
    }

    @Override
    public OrderDTO getOrderByUuid(String uuid) {
        if(ObjectUtils.isEmpty(uuid) || repository.existsByUuid(uuid)) {
            throw new UuidNotFoundOrNullException((CodeMessage.UUID_NOT_FOUND_OR_NULL));
        }
        OrderEntity entity = repository.findByUuid(uuid);
        return mapper.convertEntityToDTO(entity);
    }

    @Override
    public List<OrderDTO> getAllOrderTheseCustomer() {
        if(ObjectUtils.isEmpty(this)) {
            throw new ListIsEmptyException(CodeMessage.LIST_IS_EMPTY);
        }
        return repository
                .findAll().stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDTO updateOrderByUuidAndNewItems(String uuid, DataToCreateOrderDTO dto) {
        if(ObjectUtils.isEmpty(uuid) || repository.existsByUuid(uuid)) {
            throw new UuidNotFoundOrNullException((CodeMessage.UUID_NOT_FOUND_OR_NULL));
        }
        if(validateIfDtoFieldIsNotNullOrEmpty(dto)) {
            throw new DtoNullOrIsEmptyException(CodeMessage.DTO_NULL_OR_IS_EMPTY);
        }
        OrderEntity entity = repository.findByUuid(uuid);
        entity.setUuid(dto.getUuid());
        entity.setCustomerEntity(dto.getCustomerEntity());
        entity.setStatus(dto.getStatus());
        entity.setOrderDate(dto.getOrderDate());
        entity.setTotal(dto.getTotal());

        repository.save(entity);

        return mapper.convertEntityToDTO(entity);
    }

    @Override
    @Transactional
    public void deleteOrderByUuid(String uuid) {
        if(ObjectUtils.isEmpty(uuid) || repository.existsByUuid(uuid)) {
            throw new UuidNotFoundOrNullException((CodeMessage.UUID_NOT_FOUND_OR_NULL));
        }
        repository.deleteByUuid(uuid);
    }

    private OrderDTO convertEntityToDTO(OrderEntity entity) {
        return mapper.convertEntityToDTO(entity);
    }

    private boolean validateIfDtoFieldIsNotNullOrEmpty(DataToCreateOrderDTO dto) {
        return ObjectUtils.isEmpty(dto.getUuid()) && ObjectUtils.isEmpty(dto.getOrderDate())
                && ObjectUtils.isEmpty(dto.getCustomerEntity()) && CollectionUtils.isEmpty(dto.getProducts())
                && dto.getCustomerEntity() == null && ObjectUtils.isEmpty(dto.getStatus());
    }
}
