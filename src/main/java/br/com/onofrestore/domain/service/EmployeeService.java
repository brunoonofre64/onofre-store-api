package br.com.onofrestore.domain.service;

import br.com.onofrestore.domain.dto.employee.EmployeeInformationDTO;
import br.com.onofrestore.domain.dto.employee.EmployeeInputDTO;
import br.com.onofrestore.domain.dto.employee.EmployeeOutputDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeService {

    EmployeeOutputDTO saveNewEmployeeInDb(EmployeeInputDTO dto);

    EmployeeInformationDTO getEmployeeByUuid(String uuid);

    Page<EmployeeInformationDTO> getAllEmployeePaged(Pageable pageable);

    EmployeeOutputDTO updateEmployeeByUuid(String uuid, EmployeeInputDTO dto);

    void deleteEmployeeByUuid(String uuid);
}
