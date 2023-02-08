package br.com.onofrestore.infrastructure.mapper;

import br.com.onofrestore.domain.dto.user.UserInputDTO;
import br.com.onofrestore.domain.dto.user.UserOutpuDTO;
import br.com.onofrestore.domain.entities.UserEntity;
import br.com.onofrestore.domain.enums.CodeMessage;
import br.com.onofrestore.domain.exception.ListIsEmptyException;
import br.com.onofrestore.domain.mapper.UserMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserEntity convertDTOToEntity(UserInputDTO dto) {
        return UserEntity
                .builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .profiles(dto.getProfiles())
                .build();
    }

    @Override
    public UserOutpuDTO convertEntityToDTO(UserEntity entity) {
        return UserOutpuDTO
                .builder()
                .username(entity.getUsername())
                .profiles(entity.getProfiles())
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
