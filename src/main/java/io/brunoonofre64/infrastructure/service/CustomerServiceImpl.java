package io.brunoonofre64.infrastructure.service;

import io.brunoonofre64.api.v1.dto.CustomerDTO;
import io.brunoonofre64.api.v1.dto.DataToCreateCustomerDTO;
import io.brunoonofre64.domain.entities.CustomerEntity;
import io.brunoonofre64.domain.enums.CodeMessage;
import io.brunoonofre64.domain.exception.DtoNullOrIsEmptyException;
import io.brunoonofre64.domain.exception.ListIsEmptyException;
import io.brunoonofre64.domain.exception.UuidNotFoundOrNullException;
import io.brunoonofre64.domain.mapper.CustomerMapper;
import io.brunoonofre64.domain.service.CustomerService;
import io.brunoonofre64.infrastructure.jpa.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private CustomerMapper mapper;
    @Override
    public CustomerDTO saveNewCustomerInDb(DataToCreateCustomerDTO dto) {
        if(dto == null || dto.getName() == null || dto.getAge() == null) {
            throw new DtoNullOrIsEmptyException(CodeMessage.DTO_NULL_OR_IS_EMPTY);
        }
        CustomerEntity entity = mapper.convertDTOToEntity(dto);

        customerRepository.save(entity);

        return mapper.convertEntityToDTO(entity);
    }

    @Override
    public CustomerDTO getCustomerByUuid(String uuid) {
        if(ObjectUtils.isEmpty(uuid)) {
            throw new UuidNotFoundOrNullException(CodeMessage.UUID_NOT_FOUND_OR_NULL);
        }
        CustomerEntity entity = customerRepository.findByUuid(uuid);
        return mapper.convertEntityToDTO(entity);
    }

    @Override
    public Page<CustomerDTO> getAllCustomers(Pageable pageable) {
        if(ObjectUtils.isEmpty(this) || pageable == null) {
            throw new ListIsEmptyException(CodeMessage.LIST_IS_EMPTY);
        }
      return customerRepository.findAllBy(pageable);
    }

    @Override
    public CustomerDTO updateCustomerByUuid(String uuid, DataToCreateCustomerDTO dto) {
        if(ObjectUtils.isEmpty(uuid)) {
            throw new UuidNotFoundOrNullException(CodeMessage.UUID_NOT_FOUND_OR_NULL);
        }
        if(dto == null || dto.getName() == null || dto.getAge() == null) {
            throw new DtoNullOrIsEmptyException(CodeMessage.DTO_NULL_OR_IS_EMPTY);
        }
        CustomerEntity entity = customerRepository.findByUuid(uuid);
        entity.setName(dto.getName());
        entity.setAge(dto.getAge());

        customerRepository.save(entity);

        return mapper.convertEntityToDTO(entity);
    }
    @Override
    public void deleteCustomerOfDb(String uuid) {
        if(ObjectUtils.isEmpty(uuid)) {
            throw new UuidNotFoundOrNullException(CodeMessage.UUID_NOT_FOUND_OR_NULL);
        }
        customerRepository.deleteByUuid(uuid);
    }
}
