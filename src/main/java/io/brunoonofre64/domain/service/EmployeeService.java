package io.brunoonofre64.domain.service;

import io.brunoonofre64.domain.dto.employee.EmployeeInputDTO;
import io.brunoonofre64.domain.dto.employee.EmployeeOutputDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeService {

    EmployeeOutputDTO saveNewEmployeeInDb(EmployeeInputDTO dto);

    EmployeeOutputDTO getEmployeeByUuid(String uuid);

    Page<EmployeeOutputDTO> getAllEmployeePaged(Pageable pageable);

    EmployeeOutputDTO updateEmployeeByUuid(String uuid, EmployeeInputDTO dto);

    void deleteEmployeeByUuid(String uuid);
}
