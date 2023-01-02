package io.brunoonofre64.domain.mapper;

import io.brunoonofre64.domain.dto.employee.EmployeeInputDTO;
import io.brunoonofre64.domain.dto.employee.EmployeeOutputDTO;
import io.brunoonofre64.domain.entities.EmployeeEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public interface EmployeeMapper {

    EmployeeOutputDTO convertEntityToDTO(EmployeeEntity entity);

    EmployeeEntity convertDTOToEntity(EmployeeInputDTO dto);

    Page<EmployeeOutputDTO> mapEmployeeEntityToPageDTO(Page<EmployeeEntity> employeeList);
}
