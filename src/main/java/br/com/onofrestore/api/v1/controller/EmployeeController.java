package br.com.onofrestore.api.v1.controller;

import br.com.onofrestore.domain.dto.employee.EmployeeInformationDTO;
import br.com.onofrestore.domain.dto.employee.EmployeeInputDTO;
import br.com.onofrestore.domain.dto.employee.EmployeeOutputDTO;
import br.com.onofrestore.domain.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/funcionario")
public class EmployeeController {

    private EmployeeService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeOutputDTO saveNewEmployeeInDb(@Valid @RequestBody EmployeeInputDTO dto) {
        return service.saveNewEmployeeInDb(dto);
    }

    @GetMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeInformationDTO getEmployeeByUuid(@PathVariable String uuid) {
        return service.getEmployeeByUuid(uuid);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<EmployeeInformationDTO> getAllEmployeePaged(Pageable pageable) {
        return service.getAllEmployeePaged(pageable);
    }

    @PutMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public EmployeeOutputDTO updateEmployeeByUuid(@PathVariable String uuid,
                                                  @RequestBody EmployeeInputDTO dto) {
        return service.updateEmployeeByUuid(uuid, dto);
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployeeByUuid(@PathVariable String uuid) {
        service.deleteEmployeeByUuid(uuid);
    }
}
