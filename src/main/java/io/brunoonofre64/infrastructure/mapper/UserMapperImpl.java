package io.brunoonofre64.infrastructure.mapper;

import io.brunoonofre64.domain.dto.user.UserInputDTO;
import io.brunoonofre64.domain.dto.user.UserOutpuDTO;
import io.brunoonofre64.domain.entities.EmployeeEntity;
import io.brunoonofre64.domain.entities.UserEntity;
import io.brunoonofre64.domain.enums.CodeMessage;
import io.brunoonofre64.domain.exception.ListIsEmptyException;
import io.brunoonofre64.domain.mapper.UserMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class UserMapperImpl implements UserMapper {


    @Override
    public UserEntity convertDTOToEntity(UserInputDTO dto, EmployeeEntity employee) {
        return UserEntity
                .builder()
                .role(dto.getRole())
                .login(dto.getLogin())
                .employeeEntity(employee)
                .build();
    }

    @Override
    public UserOutpuDTO convertEntityToDTO(UserEntity entity) {
        return UserOutpuDTO
                .builder()
                .uuid(entity.getUuid())
                .login(entity.getLogin())
                .role(entity.getRole())
                .employeeUuid(entity.getEmployeeEntity().getUuid())
                .build();
    }

    @Override
    public Page<UserOutpuDTO> mapPagesUserEntityToDTO(Page<UserEntity> user) {
        if(ObjectUtils.isEmpty(user)) {
            throw new ListIsEmptyException(CodeMessage.LIST_IS_EMPTY);
        }
        return user.map(this::convertEntityToDTO);
    }
}
