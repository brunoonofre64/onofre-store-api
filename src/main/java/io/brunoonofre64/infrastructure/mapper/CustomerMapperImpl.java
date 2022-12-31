package io.brunoonofre64.infrastructure.mapper;

import io.brunoonofre64.domain.dto.customer.CustomerInputDTO;
import io.brunoonofre64.domain.dto.customer.CustomerOutputDTO;
import io.brunoonofre64.domain.entities.CustomerEntity;
import io.brunoonofre64.domain.enums.CodeMessage;
import io.brunoonofre64.domain.exception.BusinessRuleException;
import io.brunoonofre64.domain.exception.DtoNullOrIsEmptyException;
import io.brunoonofre64.domain.exception.ListIsEmptyException;
import io.brunoonofre64.domain.mapper.CustomerMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public CustomerOutputDTO convertEntityToDTO(CustomerEntity entity) {
        if(entity == null) {
            throw new BusinessRuleException(CodeMessage.ENTITY_ISNULL);
        }
        return CustomerOutputDTO
                .builder()
                .id(entity.getId())
                .name(entity.getName())
                .cpf(entity.getCpf())
                .age(entity.getAge())
                .uuid(entity.getUuid())
                .inclusionDate(entity.getInclusionDate())
                .build();
    }

    @Override
    public CustomerEntity convertDTOToEntity(CustomerInputDTO dto) {
        if(dto == null) {
            throw new DtoNullOrIsEmptyException(CodeMessage.DTO_NULL_OR_IS_EMPTY);
        }
        return CustomerEntity
                .builder()
                .name(dto.getName())
                .age(dto.getAge())
                .cpf(dto.getCpf())
                .build();
    }

    @Override
    public Page<CustomerOutputDTO> mapPagesCustomerEntityToDTO(Page<CustomerEntity> entity) {
        if(ObjectUtils.isEmpty(entity)) {
            throw new ListIsEmptyException(CodeMessage.LIST_IS_EMPTY);
        }
        return entity.map(this::convertEntityToDTO);
    }
}
