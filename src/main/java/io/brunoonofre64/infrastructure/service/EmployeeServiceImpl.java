package io.brunoonofre64.infrastructure.service;

import io.brunoonofre64.domain.dto.employee.EmployeeInputDTO;
import io.brunoonofre64.domain.dto.employee.EmployeeOutputDTO;
import io.brunoonofre64.domain.entities.EmployeeEntity;
import io.brunoonofre64.domain.enums.CodeMessage;
import io.brunoonofre64.domain.exception.DtoNullOrIsEmptyException;
import io.brunoonofre64.domain.exception.ListIsEmptyException;
import io.brunoonofre64.domain.exception.UuidNotFoundOrNullException;
import io.brunoonofre64.domain.mapper.EmployeeMapper;
import io.brunoonofre64.domain.service.EmployeeService;
import io.brunoonofre64.infrastructure.jpa.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    private EmployeeMapper mapper;

    @Override
    public EmployeeOutputDTO saveNewEmployeeInDb(EmployeeInputDTO dto) {
        validateIfDTOIsNotNull(dto);

        EmployeeEntity entity = mapper.convertDTOToEntity(dto);

        employeeRepository.save(entity);

        return mapper.convertEntityToDTO(entity);
    }

    @Override
    public EmployeeOutputDTO getEmployeeByUuid(String uuid) {
        validateIfExistsByUuidAndIsNotNull(uuid);

        EmployeeEntity employee = employeeRepository.findByUuid(uuid);

        return mapper.convertEntityToDTO(employee);
    }


    @Override
    public Page<EmployeeOutputDTO> getAllEmployeePaged(Pageable pageable) {
        if(employeeRepository.findAll(pageable).isEmpty()) {
            throw new ListIsEmptyException(CodeMessage.LIST_IS_EMPTY);
        }
        pageable = PageRequest.of(0, 10);

        Page<EmployeeEntity> employeeList = employeeRepository.findAll(pageable);

        return mapper.mapEmployeeEntityToPageDTO(employeeList);
    }

    @Override
    public EmployeeOutputDTO updateEmployeeByUuid(String uuid, EmployeeInputDTO dto) {
        validateIfExistsByUuidAndIsNotNull(uuid);
        validateIfDTOIsNotNull(dto);

        EmployeeEntity entity = employeeRepository.findByUuid(uuid);
        entity.setName(dto.getName());

        employeeRepository.save(entity);

        return mapper.convertEntityToDTO(entity);
    }

    @Override
    @Transactional
    public void deleteEmployeeByUuid(String uuid) {
        validateIfExistsByUuidAndIsNotNull(uuid);

        employeeRepository.deleteByUuid(uuid);
    }

    private static void validateIfDTOIsNotNull(EmployeeInputDTO dto) {
        if(ObjectUtils.isEmpty(dto.getName()) || ObjectUtils.isEmpty(dto.getEmail())) {
            throw new DtoNullOrIsEmptyException(CodeMessage.DTO_NULL_OR_IS_EMPTY);
        }
    }

    private void validateIfExistsByUuidAndIsNotNull(String uuid) {
        if(ObjectUtils.isEmpty(uuid) || !employeeRepository.existsByUuid(uuid)) {
            throw new UuidNotFoundOrNullException(CodeMessage.UUID_NOT_FOUND_OR_NULL);
        }
    }
}
