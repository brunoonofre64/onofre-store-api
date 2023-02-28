package br.com.onofrestore.api.v1.controller;

import br.com.onofrestore.domain.dto.employee.EmployeeInformationDTO;
import br.com.onofrestore.domain.dto.employee.EmployeeInputDTO;
import br.com.onofrestore.domain.dto.employee.EmployeeOutputDTO;
import br.com.onofrestore.domain.dto.util.SearchDTO;
import br.com.onofrestore.domain.service.EmployeeService;
import br.com.onofrestore.infrastructure.config.security.CheckSecurity;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/funcionario")
public class EmployeeController {

    private EmployeeService service;

    @CheckSecurity.Permit.CanSave
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeOutputDTO saveNewEmployeeInDb(@Valid @RequestBody EmployeeInputDTO dto) {
        return service.saveNewEmployeeInDb(dto);
    }

    @CheckSecurity.Permit.CanAuthenticated
    @GetMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeInformationDTO getEmployeeByUuid(@PathVariable String uuid) {
        return service.getEmployeeByUuid(uuid);
    }

    @CheckSecurity.Permit.CanAuthenticated
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<EmployeeInformationDTO> getAllEmployeePaged(SearchDTO dto) {
        return service.getAllEmployeePaged(dto);
    }

    @CheckSecurity.Permit.CanUpdate
    @PutMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public EmployeeOutputDTO updateEmployeeByUuid(@PathVariable String uuid,
                                                  @RequestBody EmployeeInputDTO dto) {
        return service.updateEmployeeByUuid(uuid, dto);
    }

    @CheckSecurity.Permit.CanDelete
    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployeeByUuid(@PathVariable String uuid) {
        service.deleteEmployeeByUuid(uuid);
    }
}
