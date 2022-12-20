package io.brunoonofre64.api.v1.controller;

import io.brunoonofre64.domain.dto.CustomerDTO;
import io.brunoonofre64.domain.dto.DataToCreateCustomerDTO;
import io.brunoonofre64.domain.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/cliente")
public class CustomerController {
    private CustomerService customerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO saveNewCustomerInDb(@RequestBody @Valid DataToCreateCustomerDTO dto) {
       return customerService.saveNewCustomerInDb(dto);
    }

    @GetMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO getCustomerByUuid(@PathVariable String uuid) {
        return customerService.getCustomerByUuid(uuid);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<CustomerDTO> getAllCustomers(Pageable pageable) {
        return customerService.getAllCustomers(pageable);
    }

    @PutMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CustomerDTO updateCustomerByUuid(@PathVariable String uuid,
                                            @RequestBody @Valid DataToCreateCustomerDTO dto) {
        return customerService.updateCustomerByUuid(uuid, dto);
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomerOfDb(@PathVariable String uuid) {
         customerService.deleteCustomerOfDb(uuid);
    }
}
