package io.brunoonofre64.domain.mapper;

import io.brunoonofre64.domain.dto.UserInputDTO;
import io.brunoonofre64.domain.dto.UserOutpuDTO;
import io.brunoonofre64.domain.entities.UserEntity;

public interface UserMapper {

    UserEntity convertDTOToEntity(UserInputDTO dto);

    UserOutpuDTO convertEntityToDTO(UserEntity entity);
}
