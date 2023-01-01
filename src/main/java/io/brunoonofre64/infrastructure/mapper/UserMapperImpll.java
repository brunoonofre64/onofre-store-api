package io.brunoonofre64.infrastructure.mapper;

import io.brunoonofre64.domain.dto.UserInputDTO;
import io.brunoonofre64.domain.dto.UserOutpuDTO;
import io.brunoonofre64.domain.entities.UserEntity;
import io.brunoonofre64.domain.mapper.UserMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpll implements UserMapper {


    @Override
    public UserEntity convertDTOToEntity(UserInputDTO dto) {
        return UserEntity
                .builder()
                .role(dto.getRole())
                .employeeEntity(dto.getEmployee())
                .build();
    }

    @Override
    public UserOutpuDTO convertEntityToDTO(UserEntity entity) {
        return UserOutpuDTO
                .builder()
                .role(entity.getRole())
                .employee(entity.getEmployeeEntity())
                .build();
    }
}
