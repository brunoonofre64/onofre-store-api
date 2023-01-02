package io.brunoonofre64.domain.mapper;

import io.brunoonofre64.domain.dto.user.UserInputDTO;
import io.brunoonofre64.domain.dto.user.UserOutpuDTO;
import io.brunoonofre64.domain.entities.EmployeeEntity;
import io.brunoonofre64.domain.entities.UserEntity;
import org.springframework.data.domain.Page;

public interface UserMapper {

    UserEntity convertDTOToEntity(UserInputDTO dto, EmployeeEntity employee);

    UserOutpuDTO convertEntityToDTO(UserEntity entity);

    Page<UserOutpuDTO> mapPagesUserEntityToDTO(Page<UserEntity> user);
}
