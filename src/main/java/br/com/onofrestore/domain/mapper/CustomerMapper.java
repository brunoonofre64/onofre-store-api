package br.com.onofrestore.domain.mapper;

import br.com.onofrestore.domain.entities.CustomerEntity;
import br.com.onofrestore.domain.dto.customer.CustomerInputDTO;
import br.com.onofrestore.domain.dto.customer.CustomerOutputDTO;
import org.springframework.data.domain.Page;

public interface CustomerMapper {

    CustomerOutputDTO convertEntityToDTO(CustomerEntity entity);

    CustomerEntity convertDTOToEntity(CustomerInputDTO dto);

    Page<CustomerOutputDTO> mapPagesCustomerEntityToDTO(Page<CustomerEntity> entity);
}
