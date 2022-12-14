package io.brunoonofre64.infrastructure.service;

import io.brunoonofre64.domain.dto.CustomerDTO;
import io.brunoonofre64.domain.dto.DataToCreateCustomerDTO;
import io.brunoonofre64.domain.entities.CustomerEntity;
import io.brunoonofre64.domain.enums.CodeMessage;
import io.brunoonofre64.domain.exception.*;
import io.brunoonofre64.domain.mapper.CustomerMapper;
import io.brunoonofre64.domain.service.CustomerService;
import io.brunoonofre64.infrastructure.jpa.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    private CustomerMapper mapper;

    @Override
    public CustomerDTO saveNewCustomerInDb(DataToCreateCustomerDTO dto) {
        if(dto == null || ObjectUtils.isEmpty(dto.getName()) || ObjectUtils.isEmpty(dto.getAge())) {
            throw new DtoNullOrIsEmptyException(CodeMessage.DTO_NULL_OR_IS_EMPTY);
        }
        if(customerRepository.existsByCpf(dto.getCpf())) {
            throw new CpfRepeatedException(CodeMessage.CPF_REPEATED);
        }
        CustomerEntity entity = mapper.convertDTOToEntity(dto);

        customerRepository.save(entity);

        return mapper.convertEntityToDTO(entity);
    }

    @Override
    public CustomerDTO getCustomerByUuid(String uuid) {
        if(ObjectUtils.isEmpty(uuid) || !customerRepository.existsByUuid(uuid)) {
            throw new UuidNotFoundOrNullException(CodeMessage.UUID_NOT_FOUND_OR_NULL);
        }
        CustomerEntity entity = customerRepository.findByUuid(uuid);
        return mapper.convertEntityToDTO(entity);
    }

    @Override
    public Page<CustomerDTO> getAllCustomers(Pageable pageable) {
        if(ObjectUtils.isEmpty(pageable) || pageable.isUnpaged()) {
            throw new ListIsEmptyException(CodeMessage.LIST_IS_EMPTY);
        }
        pageable = PageRequest.of(0,10);

        Page<CustomerEntity> entity = customerRepository.findAll(pageable);
        return mapPagesCustomerEntityToDTO(entity);
    }

    @Override
    public CustomerDTO updateCustomerByUuid(String uuid, DataToCreateCustomerDTO dto) {
        if(ObjectUtils.isEmpty(uuid) || !customerRepository.existsByUuid(uuid)) {
            throw new UuidNotFoundOrNullException(CodeMessage.UUID_NOT_FOUND_OR_NULL);
        }
        if(dto == null || ObjectUtils.isEmpty(dto.getName()) || ObjectUtils.isEmpty(dto.getAge())) {
            throw new DtoNullOrIsEmptyException(CodeMessage.DTO_NULL_OR_IS_EMPTY);
        }
        CustomerEntity entity = customerRepository.findByUuid(uuid);
        entity.setName(dto.getName());
        entity.setAge(dto.getAge());

        customerRepository.save(entity);

        return mapper.convertEntityToDTO(entity);
    }

    @Override
    @Transactional
    public void deleteCustomerOfDb(String uuid) {
        if(ObjectUtils.isEmpty(uuid) || !customerRepository.existsByUuid(uuid)) {
            throw new UuidNotFoundOrNullException(CodeMessage.UUID_NOT_FOUND_OR_NULL);
        }
        customerRepository.deleteByUuid(uuid);
    }

    public Page<CustomerDTO> mapPagesCustomerEntityToDTO(Page<CustomerEntity> entity) {
        if(ObjectUtils.isEmpty(entity)) {
            throw new ListIsEmptyException(CodeMessage.LIST_IS_EMPTY);
        }
        return entity.map(this::mapPagetoDto);
    }

    private CustomerDTO mapPagetoDto(CustomerEntity entity) {
        if(ObjectUtils.isEmpty(entity)) {
            throw new CustomerException(CodeMessage.FIELD_NULL_OR_IS_EMPTY);
        }
        return mapper.convertEntityToDTO(entity);
    }
}
