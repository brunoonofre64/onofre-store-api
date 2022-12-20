package io.brunoonofre64.infrastructure.mapper;

import io.brunoonofre64.domain.dto.CustomerDTO;
import io.brunoonofre64.domain.dto.DataToCreateCustomerDTO;
import io.brunoonofre64.domain.entities.CustomerEntity;
import io.brunoonofre64.domain.enums.CodeMessage;
import io.brunoonofre64.domain.exception.CustomerException;
import io.brunoonofre64.domain.exception.DtoNullOrIsEmptyException;
import io.brunoonofre64.domain.exception.ListIsEmptyException;
import io.brunoonofre64.domain.mapper.CustomerMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public CustomerDTO convertEntityToDTO(CustomerEntity entity) {
        if(entity == null) {
            throw new CustomerException(CodeMessage.ENTITY_ISNULL);
        }
        CustomerDTO dto = new CustomerDTO();

        dto.setName(entity.getName());
        dto.setCpf(entity.getCpf());
        dto.setAge(entity.getAge());
        dto.setUuid(entity.getUuid());
        dto.setInclusionDate(entity.getInclusionDate());
        return dto;
    }

    @Override
    public CustomerEntity convertDTOToEntity(DataToCreateCustomerDTO dto) {
        if(dto == null || ObjectUtils.isEmpty(dto.getAge())
                || ObjectUtils.isEmpty(dto.getCpf()) || ObjectUtils.isEmpty(dto.getName())) {
            throw new DtoNullOrIsEmptyException(CodeMessage.DTO_NULL_OR_IS_EMPTY);
        }
        CustomerEntity entity = new CustomerEntity();

        entity.setName(dto.getName());
        entity.setAge(dto.getAge());
        entity.setCpf(dto.getCpf());
        return entity;
    }

    @Override
    public Page<CustomerDTO> mapPagesCustomerEntityToDTO(Page<CustomerEntity> entity) {
        if(ObjectUtils.isEmpty(entity)) {
            throw new ListIsEmptyException(CodeMessage.LIST_IS_EMPTY);
        }
        return entity.map(this::convertEntityToDTO);
    }
}
