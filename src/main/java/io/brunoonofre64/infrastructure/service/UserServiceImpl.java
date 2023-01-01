package io.brunoonofre64.infrastructure.service;

import io.brunoonofre64.domain.dto.UserInputDTO;
import io.brunoonofre64.domain.dto.UserOutpuDTO;
import io.brunoonofre64.domain.entities.UserEntity;
import io.brunoonofre64.domain.enums.CodeMessage;
import io.brunoonofre64.domain.exception.DtoNullOrIsEmptyException;
import io.brunoonofre64.domain.mapper.UserMapper;
import io.brunoonofre64.domain.service.UserService;
import io.brunoonofre64.infrastructure.jpa.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository repository;

    private UserMapper mapper;

    @Override
    public UserOutpuDTO saveNewUserIndDB(UserInputDTO userDTO) {
        if(ObjectUtils.isEmpty(userDTO)) {
            throw new DtoNullOrIsEmptyException(CodeMessage.DTO_NULL_OR_IS_EMPTY);
        }
        UserEntity entity = mapper.convertDTOToEntity(userDTO);

        repository.save(entity);

        return mapper.convertEntityToDTO(entity);
    }

    @Override
    public UserOutpuDTO getUserByUuid(String uuid) {
        return null;
    }

    @Override
    public UserOutpuDTO updateUserByUui(String uuid, UserInputDTO dto) {
        return null;
    }

    @Override
    public void deleteUserByUuid(String uuid) {

    }
}
