package io.brunoonofre64.infrastructure.mapper;

import io.brunoonofre64.domain.dto.CustomerDTO;
import io.brunoonofre64.domain.dto.DataToCreateCustomerDTO;
import io.brunoonofre64.domain.entities.CustomerEntity;
import io.brunoonofre64.domain.enums.CodeMessage;
import io.brunoonofre64.domain.exception.CustomerException;
import io.brunoonofre64.domain.exception.DtoNullOrIsEmptyException;
import io.brunoonofre64.domain.mapper.CustomerMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapperImpl implements CustomerMapper {
    @Override
    public CustomerDTO convertEntityToDTO(CustomerEntity entity) {
        if(entity == null) {
            throw new CustomerException(CodeMessage.ENTITY_ISNULL);
        }
        CustomerDTO dto = new CustomerDTO();

        dto.setName(entity.getName());
        dto.setAge(entity.getAge());
        dto.setUuid(entity.getUuid());
        dto.setInclusionDate(entity.getInclusionDate());
        return dto;
    }

    @Override
    public CustomerEntity convertDTOToEntity(DataToCreateCustomerDTO dto) {
        if(dto == null || dto.getName() == null || dto.getAge() == null) {
            throw new DtoNullOrIsEmptyException(CodeMessage.DTO_NULL_OR_IS_EMPTY);
        }
        CustomerEntity entity = new CustomerEntity();

        entity.setName(dto.getName());
        entity.setAge(dto.getAge());
        return entity;
    }
}
