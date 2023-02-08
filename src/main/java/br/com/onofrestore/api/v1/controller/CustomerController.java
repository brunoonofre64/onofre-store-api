package br.com.onofrestore.api.v1.controller;

import br.com.onofrestore.domain.dto.customer.CustomerInputDTO;
import br.com.onofrestore.domain.dto.customer.CustomerOutputDTO;
import br.com.onofrestore.domain.service.CustomerService;
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
    public CustomerOutputDTO saveNewCustomerInDb(@Valid @RequestBody CustomerInputDTO dto) {
       return customerService.saveNewCustomerInDb(dto);
    }

    @GetMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerOutputDTO getCustomerByUuid(@PathVariable String uuid) {
        return customerService.getCustomerByUuid(uuid);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<CustomerOutputDTO> getAllCustomers(Pageable pageable) {
        return customerService.getAllCustomers(pageable);
    }

    @PutMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CustomerOutputDTO updateCustomerByUuid(@PathVariable String uuid,
                                                  @RequestBody @Valid CustomerInputDTO dto) {
        return customerService.updateCustomerByUuid(uuid, dto);
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomerOfDb(@PathVariable String uuid) {
         customerService.deleteCustomerOfDb(uuid);
    }
}
