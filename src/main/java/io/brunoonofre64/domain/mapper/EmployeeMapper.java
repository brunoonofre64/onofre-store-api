package io.brunoonofre64.domain.mapper;

import io.brunoonofre64.domain.dto.employee.EmployeeInformationDTO;
import io.brunoonofre64.domain.dto.employee.EmployeeInputDTO;
import io.brunoonofre64.domain.dto.employee.EmployeeOutputDTO;
import io.brunoonofre64.domain.entities.EmployeeEntity;
import io.brunoonofre64.domain.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public interface EmployeeMapper {

    EmployeeOutputDTO convertEntityToDTO(EmployeeEntity entity, UserEntity user);

    EmployeeEntity convertDTOToEntity(EmployeeInputDTO dto);

    EmployeeOutputDTO convertEntityToDTO(EmployeeEntity entity);

    EmployeeInformationDTO convertInInformationDTO(EmployeeEntity entity);

    Page<EmployeeInformationDTO> mapEmployeeEntityToPageDTO(Page<EmployeeEntity> employeeList);
}
