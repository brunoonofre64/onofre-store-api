package io.brunoonofre64.infrastructure.service;

import io.brunoonofre64.domain.dto.user.UserInputDTO;
import io.brunoonofre64.domain.dto.user.UserOutpuDTO;
import io.brunoonofre64.domain.entities.EmployeeEntity;
import io.brunoonofre64.domain.entities.UserEntity;
import io.brunoonofre64.domain.enums.CodeMessage;
import io.brunoonofre64.domain.exception.DtoNullOrIsEmptyException;
import io.brunoonofre64.domain.exception.ListIsEmptyException;
import io.brunoonofre64.domain.exception.UuidNotFoundOrNullException;
import io.brunoonofre64.domain.mapper.UserMapper;
import io.brunoonofre64.domain.service.UserService;
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
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private EmployeeRepository employeeRepository;

    private UserMapper mapper;

    @Override
    public UserOutpuDTO saveNewUserIndDB(UserInputDTO userDTO) {
        validateIfInputDtoIsNotNull(userDTO);

        EmployeeEntity employee = getEmployeeIfExistsByUuidInsideDTO(userDTO);
        UserEntity entity = mapper.convertDTOToEntity(userDTO, employee);

        userRepository.save(entity);

        return mapper.convertEntityToDTO(entity);
    }

    @Override
    public UserOutpuDTO getUserByUuid(String uuid) {
        validateIfExistsUuidAndIsNotNull(uuid);
        UserEntity user = userRepository.findByUuid(uuid);

        return mapper.convertEntityToDTO(user);
    }

    @Override
    public Page<UserOutpuDTO> getAllUserPaged(Pageable pageable) {
        if(userRepository.findAll(pageable).isEmpty()) {
            throw new ListIsEmptyException(CodeMessage.LIST_IS_EMPTY);
        }
        pageable = PageRequest.of(0, 10);

        Page<UserEntity> userList = userRepository.findAll(pageable);

        return mapper.mapPagesUserEntityToDTO(userList);
    }

    @Override
    public UserOutpuDTO updateUserByUui(String uuid, UserInputDTO dto) {
        validateIfExistsUuidAndIsNotNull(uuid);
        validateIfInputDtoIsNotNull(dto);

        EmployeeEntity employee = getEmployeeIfExistsByUuidInsideDTO(dto);
        UserEntity user = userRepository.findByUuid(uuid);

        user.setLogin(dto.getLogin());
        user.setRole(dto.getRole());
        user.setEmployeeEntity(employee);

        userRepository.save(user);

        return mapper.convertEntityToDTO(user);
    }

    @Override
    @Transactional
    public void deleteUserByUuid(String uuid) {
        validateIfExistsUuidAndIsNotNull(uuid);

        userRepository.deleteByUuid(uuid);
    }

    private static void validateIfInputDtoIsNotNull(UserInputDTO userDTO) {
        if(ObjectUtils.isEmpty(userDTO.getLogin()) || ObjectUtils.isEmpty(userDTO.getRole())) {
            throw new DtoNullOrIsEmptyException(CodeMessage.DTO_NULL_OR_IS_EMPTY);
        }
    }

    private void validateIfExistsUuidAndIsNotNull(String uuid) {
        if(ObjectUtils.isEmpty(uuid) || !userRepository.existsByUuid(uuid)) {
            throw new UuidNotFoundOrNullException(CodeMessage.UUID_NOT_FOUND_OR_NULL);
        }
    }

    private EmployeeEntity getEmployeeIfExistsByUuidInsideDTO(UserInputDTO dto) {
        validateIfInputDtoIsNotNull(dto);

        String uuid = dto.getEmployeeUuid();

        try {
            return employeeRepository.findByUuid(uuid);
        } catch (Exception ex) {
            throw new UuidNotFoundOrNullException(CodeMessage.UUID_NOT_FOUND_OR_NULL);
        }
    }
}
