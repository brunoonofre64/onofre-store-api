package br.com.onofrestore.domain.service;

import br.com.onofrestore.domain.dto.employee.EmployeeInformationDTO;
import br.com.onofrestore.domain.dto.employee.EmployeeInputDTO;
import br.com.onofrestore.domain.dto.employee.EmployeeOutputDTO;
import br.com.onofrestore.domain.dto.util.SearchDTO;
import org.springframework.data.domain.Page;

public interface EmployeeService {
    EmployeeOutputDTO saveNewEmployeeInDb(EmployeeInputDTO dto);
    EmployeeInformationDTO getEmployeeByUuid(String uuid);
    Page<EmployeeInformationDTO> getAllEmployeePaged(SearchDTO dto);
    EmployeeOutputDTO updateEmployeeByUuid(String uuid, EmployeeInputDTO dto);
    void deleteEmployeeByUuid(String uuid);
}
