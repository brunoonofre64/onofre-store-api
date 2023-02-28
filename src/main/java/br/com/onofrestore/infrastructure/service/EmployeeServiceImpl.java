package br.com.onofrestore.infrastructure.service;

import br.com.onofrestore.domain.dto.employee.EmployeeInformationDTO;
import br.com.onofrestore.domain.dto.employee.EmployeeInputDTO;
import br.com.onofrestore.domain.dto.employee.EmployeeOutputDTO;
import br.com.onofrestore.domain.dto.util.SearchDTO;
import br.com.onofrestore.domain.entities.EmployeeEntity;
import br.com.onofrestore.domain.enums.CodeMessage;
import br.com.onofrestore.domain.exception.DtoNullOrIsEmptyException;
import br.com.onofrestore.domain.exception.EmployeeAlreadyExists;
import br.com.onofrestore.domain.exception.EmployeeNotFound;
import br.com.onofrestore.domain.exception.UuidNotFoundOrNullException;
import br.com.onofrestore.domain.mapper.EmployeeMapper;
import br.com.onofrestore.domain.service.EmployeeService;
import br.com.onofrestore.infrastructure.jpa.repositories.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private EmployeeMapper mapper;

    @Override
    public EmployeeOutputDTO saveNewEmployeeInDb(EmployeeInputDTO dto) {
        this.validateEmployee(dto);
        this.mapperToLoweCase(dto);

        if (employeeRepository.existsByCpf(dto.getCpf())) {
            throw new EmployeeAlreadyExists(CodeMessage.CPF_REPEATED);
        }

        EmployeeEntity entity = mapper.convertDTOToEntity(dto);

        employeeRepository.save(entity);

        return mapper.convertEntityToDTO(entity);
    }

    @Override
    public EmployeeInformationDTO getEmployeeByUuid(String uuid) {
        if (uuid == null) {
            throw new UuidNotFoundOrNullException(CodeMessage.UUID_NOT_FOUND_OR_NULL);
        }

        EmployeeEntity employee = employeeRepository.findByUuid(uuid);

        if (employee == null) {
            throw new EmployeeNotFound(CodeMessage.EMPLOYEE_NOT_FOUND);
        }

        return mapper.convertInInformationDTO(employee);
    }


    @Override
    public Page<EmployeeInformationDTO> getAllEmployeePaged(SearchDTO dto) {
        Pageable pageable = this.getSortedByInclusionDate(dto);

        Page<EmployeeEntity> employeeList = employeeRepository.findAll(pageable);

        return mapper.mapEmployeeEntityToPageDTO(employeeList);
    }

    @Override
    public EmployeeOutputDTO updateEmployeeByUuid(String uuid, EmployeeInputDTO dto) {
        if (dto == null) {
            throw new DtoNullOrIsEmptyException(CodeMessage.DTO_NULL_OR_IS_EMPTY);
        }
        if (uuid == null) {
            throw new UuidNotFoundOrNullException(CodeMessage.UUID_NOT_FOUND_OR_NULL);
        }
        this.mapperToLoweCase(dto);

        EmployeeEntity entity = employeeRepository.findByUuid(uuid);

        if (entity == null) {
            throw new EmployeeNotFound(CodeMessage.EMPLOYEE_NOT_FOUND);
        }

        if (dto.getName() != null) {entity.setName(dto.getName());}
        if (dto.getEmail() != null) {entity.setEmail(dto.getEmail());}

        employeeRepository.save(entity);

        return mapper.convertEntityToDTO(entity);
    }

    @Override
    @Transactional
    public void deleteEmployeeByUuid(String uuid) {
        if (uuid == null) {
            throw new UuidNotFoundOrNullException(CodeMessage.UUID_NOT_FOUND_OR_NULL);
        }

        try {
            employeeRepository.deleteByUuid(uuid);
        } catch (Exception ex) {
            throw new EmployeeNotFound(CodeMessage.EMPLOYEE_NOT_FOUND);
        }
    }

    private void validateEmployee(EmployeeInputDTO dto) {
        if (isEmpty(dto.getName()) || isEmpty(dto.getEmail())) {
            throw new DtoNullOrIsEmptyException(CodeMessage.DTO_NULL_OR_IS_EMPTY);
        }
    }

    private Pageable getSortedByInclusionDate(SearchDTO dto) {
        return PageRequest.of(dto.getPage(), dto.getSize(),
                Sort.by("inclusionDate").descending());
    }

    private void mapperToLoweCase(EmployeeInputDTO dto) {
        if (!isEmpty(dto.getEmail())) {
            dto.setEmail(dto.getEmail().toLowerCase());
        }
        if (!isEmpty(dto.getName())) {
            dto.setEmail(dto.getEmail().toLowerCase());
        }
    }
}
