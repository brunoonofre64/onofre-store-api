package br.com.onofrestore.domain.mapper;

import br.com.onofrestore.domain.entities.UserEntity;
import br.com.onofrestore.domain.dto.user.UserInputDTO;
import br.com.onofrestore.domain.dto.user.UserOutpuDTO;
import org.springframework.data.domain.Page;

public interface UserMapper {

    UserEntity convertDTOToEntity(UserInputDTO dto);

    UserOutpuDTO convertEntityToDTO(UserEntity entity);

    Page<UserOutpuDTO> mapPagesUserEntityToDTO(Page<UserEntity> user);
}
