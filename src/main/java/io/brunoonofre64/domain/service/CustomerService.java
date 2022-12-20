package io.brunoonofre64.domain.service;

import io.brunoonofre64.domain.dto.CustomerDTO;
import io.brunoonofre64.domain.dto.DataToCreateCustomerDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {

    CustomerDTO saveNewCustomerInDb(DataToCreateCustomerDTO dto);

    CustomerDTO getCustomerByUuid(String uuid);

    Page<CustomerDTO> getAllCustomers(Pageable pageable);

    CustomerDTO updateCustomerByUuid(String uuid, DataToCreateCustomerDTO dto);

    void deleteCustomerOfDb(String uuid);
}
