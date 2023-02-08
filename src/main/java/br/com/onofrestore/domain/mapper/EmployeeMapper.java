package br.com.onofrestore.domain.mapper;

import br.com.onofrestore.domain.entities.EmployeeEntity;
import br.com.onofrestore.domain.dto.employee.EmployeeInformationDTO;
import br.com.onofrestore.domain.dto.employee.EmployeeInputDTO;
import br.com.onofrestore.domain.dto.employee.EmployeeOutputDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public interface EmployeeMapper {

    EmployeeEntity convertDTOToEntity(EmployeeInputDTO dto);

    EmployeeOutputDTO convertEntityToDTO(EmployeeEntity entity);

    EmployeeInformationDTO convertInInformationDTO(EmployeeEntity entity);

    Page<EmployeeInformationDTO> mapEmployeeEntityToPageDTO(Page<EmployeeEntity> employeeList);
}
