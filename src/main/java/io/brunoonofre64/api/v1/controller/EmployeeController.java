package io.brunoonofre64.api.v1.controller;

import io.brunoonofre64.domain.dto.employee.EmployeeInputDTO;
import io.brunoonofre64.domain.dto.employee.EmployeeOutputDTO;
import io.brunoonofre64.domain.service.EmployeeService;
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
    public EmployeeOutputDTO getEmployeeByUuid(@PathVariable String uuid) {
        return service.getEmployeeByUuid(uuid);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<EmployeeOutputDTO> getAllEmployeePaged(Pageable pageable) {
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
