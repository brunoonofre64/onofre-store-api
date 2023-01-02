package io.brunoonofre64.infrastructure.service;

import io.brunoonofre64.domain.dto.employee.EmployeeInformationDTO;
import io.brunoonofre64.domain.dto.employee.EmployeeInputDTO;
import io.brunoonofre64.domain.dto.employee.EmployeeOutputDTO;
import io.brunoonofre64.domain.entities.EmployeeEntity;
import io.brunoonofre64.domain.entities.UserEntity;
import io.brunoonofre64.domain.enums.CodeMessage;
import io.brunoonofre64.domain.exception.DtoNullOrIsEmptyException;
import io.brunoonofre64.domain.exception.ListIsEmptyException;
import io.brunoonofre64.domain.exception.UuidNotFoundOrNullException;
import io.brunoonofre64.domain.mapper.EmployeeMapper;
import io.brunoonofre64.domain.service.EmployeeService;
import io.brunoonofre64.infrastructure.jpa.EmployeeRepository;
import io.brunoonofre64.infrastructure.jpa.UserRepository;
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

    private UserRepository userRepository;

    private EmployeeMapper mapper;

    @Override
    public EmployeeOutputDTO saveNewEmployeeInDb(EmployeeInputDTO dto) {
        validateEmployee(dto);

        UserEntity user = getUserInDatabase(dto);
        EmployeeEntity entity = mapper.convertDTOToEntity(dto);
        entity.setUser(user);

        employeeRepository.save(entity);

        return mapper.convertEntityToDTO(entity, user);
    }

    @Override
    public EmployeeInformationDTO getEmployeeByUuid(String uuid) {
        validateEmployeeUuid(uuid);

        EmployeeEntity employee = employeeRepository.findByUuid(uuid);

        return mapper.convertInInformationDTO(employee);
    }


    @Override
    public Page<EmployeeInformationDTO> getAllEmployeePaged(Pageable pageable) {
        if(employeeRepository.findAll(pageable).isEmpty()) {
            throw new ListIsEmptyException(CodeMessage.LIST_IS_EMPTY);
        }
        pageable = PageRequest.of(0, 10);

        Page<EmployeeEntity> employeeList = employeeRepository.findAll(pageable);

        return mapper.mapEmployeeEntityToPageDTO(employeeList);
    }

    @Override
    public EmployeeOutputDTO updateEmployeeByUuid(String uuid, EmployeeInputDTO dto) {
        validateEmployeeUuid(uuid);
        validateEmployee(dto);

        UserEntity user = getUserInDatabase(dto);
        EmployeeEntity entity = employeeRepository.findByUuid(uuid);
        entity.setName(dto.getName());

        employeeRepository.save(entity);

        return mapper.convertEntityToDTO(entity, user);
    }

    @Override
    @Transactional
    public void deleteEmployeeByUuid(String uuid) {
        validateEmployeeUuid(uuid);

        employeeRepository.deleteByUuid(uuid);
    }

    private UserEntity getUserInDatabase(EmployeeInputDTO dto) {
        if(ObjectUtils.isEmpty(dto.getUserUuid()) || !userRepository.existsByUuid(dto.getUserUuid())) {
            throw new UuidNotFoundOrNullException(CodeMessage.UUID_NOT_FOUND_OR_NULL);
        }
        String uuid = dto.getUserUuid();

        try {
            return userRepository.findByUuid(uuid);
        } catch(Exception ex) {
            throw new UuidNotFoundOrNullException(CodeMessage.UUID_NOT_FOUND_OR_NULL);
        }
    }

    private static void validateEmployee(EmployeeInputDTO dto) {
        if(ObjectUtils.isEmpty(dto.getName()) || ObjectUtils.isEmpty(dto.getEmail())) {
            throw new DtoNullOrIsEmptyException(CodeMessage.DTO_NULL_OR_IS_EMPTY);
        }
    }

    private void validateEmployeeUuid(String uuid) {
        if(ObjectUtils.isEmpty(uuid) || !employeeRepository.existsByUuid(uuid)) {
            throw new UuidNotFoundOrNullException(CodeMessage.UUID_NOT_FOUND_OR_NULL);
        }
    }
}
