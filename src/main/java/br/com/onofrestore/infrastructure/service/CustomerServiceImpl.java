package br.com.onofrestore.infrastructure.service;

import br.com.onofrestore.domain.dto.customer.CustomerInputDTO;
import br.com.onofrestore.domain.dto.customer.CustomerOutputDTO;
import br.com.onofrestore.domain.entities.CustomerEntity;
import br.com.onofrestore.domain.enums.CodeMessage;
import br.com.onofrestore.domain.exception.CpfRepeatedException;
import br.com.onofrestore.domain.exception.DtoNullOrIsEmptyException;
import br.com.onofrestore.domain.exception.ListIsEmptyException;
import br.com.onofrestore.domain.exception.UuidNotFoundOrNullException;
import br.com.onofrestore.domain.mapper.CustomerMapper;
import br.com.onofrestore.domain.service.CustomerService;
import br.com.onofrestore.infrastructure.jpa.CustomerRepository;
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

    private CustomerRepository repository;
    private CustomerMapper mapper;

    @Override
    public CustomerOutputDTO saveNewCustomerInDb(CustomerInputDTO dto) {
        this.validateCustomer(dto);

        if(repository.existsByCpf(dto.getCpf())) {
            throw new CpfRepeatedException(CodeMessage.CPF_REPEATED);
        }
        CustomerEntity entity = mapper.convertDTOToEntity(dto);

        repository.save(entity);

        return mapper.convertEntityToDTO(entity);
    }

    @Override
    public CustomerOutputDTO getCustomerByUuid(String uuid) {
        validateCustomerUuid(uuid);

        CustomerEntity entity = repository.findByUuid(uuid);

        return mapper.convertEntityToDTO(entity);
    }

    @Override
    public Page<CustomerOutputDTO> getAllCustomers(Pageable pageable) {
        if(ObjectUtils.isEmpty(pageable) || pageable.isUnpaged()) {
            throw new ListIsEmptyException(CodeMessage.LIST_IS_EMPTY);
        }
        pageable = PageRequest.of(0,10);

        Page<CustomerEntity> entity = repository.findAll(pageable);
        return mapper.mapPagesCustomerEntityToDTO(entity);
    }

    @Override
    public CustomerOutputDTO updateCustomerByUuid(String uuid, CustomerInputDTO dto) {
        this.validateCustomer(dto);
        this.validateCustomerUuid(uuid);

        CustomerEntity entity = repository.findByUuid(uuid);
        entity.setName(dto.getName());
        entity.setAge(dto.getAge());

        repository.save(entity);

        return mapper.convertEntityToDTO(entity);
    }

    @Override
    @Transactional
    public void deleteCustomerOfDb(String uuid) {
        this.validateCustomerUuid(uuid);

        repository.deleteByUuid(uuid);
    }

    private void validateCustomerUuid(String uuid) {
        if(ObjectUtils.isEmpty(uuid) || !repository.existsByUuid(uuid)) {
            throw new UuidNotFoundOrNullException(CodeMessage.UUID_NOT_FOUND_OR_NULL);
        }
    }

    private void validateCustomer(CustomerInputDTO dto) {
        if(dto == null || ObjectUtils.isEmpty(dto.getName()) || ObjectUtils.isEmpty(dto.getAge())) {
            throw new DtoNullOrIsEmptyException(CodeMessage.DTO_NULL_OR_IS_EMPTY);
        }
    }
}
