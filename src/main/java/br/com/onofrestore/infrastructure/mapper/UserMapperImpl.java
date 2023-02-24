package br.com.onofrestore.infrastructure.mapper;

import br.com.onofrestore.domain.dto.user.UserInputDTO;
import br.com.onofrestore.domain.dto.user.UserOutpuDTO;
import br.com.onofrestore.domain.entities.RoleEntity;
import br.com.onofrestore.domain.entities.UserEntity;
import br.com.onofrestore.domain.enums.CodeMessage;
import br.com.onofrestore.domain.exception.ListIsEmptyException;
import br.com.onofrestore.domain.mapper.UserMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserEntity convertDTOToEntity(UserInputDTO dto, Set<RoleEntity> roles) {
        return UserEntity
                .builder()
                .username(dto.getUsername())
                .fullName(dto.getFullName())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .roles(roles)
                .build();
    }

    @Override
    public UserOutpuDTO convertEntityToDTO(UserEntity entity) {
        Set<String> profiles = entity.getRoles()
                .stream()
                .map(RoleEntity::getProfile)
                .collect(Collectors.toSet());

        return UserOutpuDTO.builder()
                .username(entity.getUsername())
                .fullName(entity.getFullName())
                .uuid(entity.getUuid())
                .profiles(profiles)
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
