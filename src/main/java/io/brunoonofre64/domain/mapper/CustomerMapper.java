package io.brunoonofre64.domain.mapper;

import io.brunoonofre64.domain.dto.CustomerDTO;
import io.brunoonofre64.domain.dto.DataToCreateCustomerDTO;
import io.brunoonofre64.domain.entities.CustomerEntity;

public interface CustomerMapper {
    CustomerDTO convertEntityToDTO(CustomerEntity entity);
    CustomerEntity convertDTOToEntity(DataToCreateCustomerDTO dto);
}
