package br.com.onofrestore.domain.service;

import br.com.onofrestore.domain.dto.customer.CustomerOutputDTO;
import br.com.onofrestore.domain.dto.customer.CustomerInputDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {

    CustomerOutputDTO saveNewCustomerInDb(CustomerInputDTO dto);

    CustomerOutputDTO getCustomerByUuid(String uuid);

    Page<CustomerOutputDTO> getAllCustomers(Pageable pageable);

    CustomerOutputDTO updateCustomerByUuid(String uuid, CustomerInputDTO dto);

    void deleteCustomerOfDb(String uuid);
}
