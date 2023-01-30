package io.brunoonofre64.infrastructure.mapper;

import io.brunoonofre64.domain.dto.employee.EmployeeInformationDTO;
import io.brunoonofre64.domain.dto.employee.EmployeeInputDTO;
import io.brunoonofre64.domain.dto.employee.EmployeeOutputDTO;
import io.brunoonofre64.domain.entities.EmployeeEntity;
import io.brunoonofre64.domain.enums.CodeMessage;
import io.brunoonofre64.domain.exception.BusinessRuleException;
import io.brunoonofre64.domain.exception.ListIsEmptyException;
import io.brunoonofre64.domain.mapper.EmployeeMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public EmployeeInformationDTO convertInInformationDTO(EmployeeEntity entity) {
        if(entity == null) {
            throw new BusinessRuleException(CodeMessage.OBJECTS_ISNULL_OR_EMPTY);
        }
        return EmployeeInformationDTO
                .builder()
                .name(entity.getName())
                .uuid(entity.getUuid())
                .email(entity.getEmail())
                .cpf(entity.getCpf())
                .build();
    }

    @Override
    public EmployeeEntity convertDTOToEntity(EmployeeInputDTO dto) {
        if(dto == null) {
            throw new BusinessRuleException(CodeMessage.OBJECTS_ISNULL_OR_EMPTY);
        }
        return EmployeeEntity
                .builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .cpf(dto.getCpf())
                .build();
    }

    @Override
    public EmployeeOutputDTO convertEntityToDTO(EmployeeEntity entity) {
        if(entity == null) {
            throw new BusinessRuleException(CodeMessage.OBJECTS_ISNULL_OR_EMPTY);
        }
        return EmployeeOutputDTO
                .builder()
                .name(entity.getName())
                .uuid(entity.getUuid())
                .email(entity.getEmail())
                .cpf(entity.getCpf())
                .build();
    }

    @Override
    public Page<EmployeeInformationDTO> mapEmployeeEntityToPageDTO(Page<EmployeeEntity> employeeList) {
        if(ObjectUtils.isEmpty(employeeList)) {
         throw new ListIsEmptyException(CodeMessage.LIST_IS_EMPTY);
        }
        return employeeList.map(this::convertInInformationDTO);
    }
}
