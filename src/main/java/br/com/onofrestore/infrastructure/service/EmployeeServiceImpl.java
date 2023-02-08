package br.com.onofrestore.infrastructure.service;

import br.com.onofrestore.domain.dto.employee.EmployeeInformationDTO;
import br.com.onofrestore.domain.dto.employee.EmployeeInputDTO;
import br.com.onofrestore.domain.dto.employee.EmployeeOutputDTO;
import br.com.onofrestore.domain.entities.EmployeeEntity;
import br.com.onofrestore.domain.enums.CodeMessage;
import br.com.onofrestore.domain.exception.DtoNullOrIsEmptyException;
import br.com.onofrestore.domain.exception.ListIsEmptyException;
import br.com.onofrestore.domain.exception.UuidNotFoundOrNullException;
import br.com.onofrestore.domain.mapper.EmployeeMapper;
import br.com.onofrestore.domain.service.EmployeeService;
import br.com.onofrestore.infrastructure.jpa.EmployeeRepository;
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
        validateEmployee(dto);

        EmployeeEntity entity = mapper.convertDTOToEntity(dto);

        employeeRepository.save(entity);

        return mapper.convertEntityToDTO(entity);
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
        this.validateEmployeeUuid(uuid);
        this.validateEmployee(dto);

        EmployeeEntity entity = employeeRepository.findByUuid(uuid);
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());

        employeeRepository.save(entity);

        return mapper.convertEntityToDTO(entity);
    }

    @Override
    @Transactional
    public void deleteEmployeeByUuid(String uuid) {
        this.validateEmployeeUuid(uuid);

        employeeRepository.deleteByUuid(uuid);
    }

    private void validateEmployee(EmployeeInputDTO dto) {
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
