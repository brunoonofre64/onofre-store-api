package io.brunoonofre64.domain.mapper;

import io.brunoonofre64.domain.dto.CustomerOutputDTO;
import io.brunoonofre64.domain.dto.CustomerInputDTO;
import io.brunoonofre64.domain.entities.CustomerEntity;
import org.springframework.data.domain.Page;

public interface CustomerMapper {

    CustomerOutputDTO convertEntityToDTO(CustomerEntity entity);

    CustomerEntity convertDTOToEntity(CustomerInputDTO dto);

    Page<CustomerOutputDTO> mapPagesCustomerEntityToDTO(Page<CustomerEntity> entity);
}
