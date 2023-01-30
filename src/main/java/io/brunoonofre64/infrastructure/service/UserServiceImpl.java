package io.brunoonofre64.infrastructure.service;

import io.brunoonofre64.domain.dto.user.UserInputDTO;
import io.brunoonofre64.domain.dto.user.UserOutpuDTO;
import io.brunoonofre64.domain.entities.UserEntity;
import io.brunoonofre64.domain.enums.CodeMessage;
import io.brunoonofre64.domain.exception.DtoNullOrIsEmptyException;
import io.brunoonofre64.domain.exception.LoginNotFoundException;
import io.brunoonofre64.domain.mapper.UserMapper;
import io.brunoonofre64.infrastructure.jpa.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserDetailsService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new LoginNotFoundException(CodeMessage.USER_NOTFOUND));

        return User
                .builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getAuthorities())
                .build();
    }

    public UserOutpuDTO saveNewUserInDb(UserInputDTO userInputDTO) {
        this.validateUserInputDTO(userInputDTO);

        UserEntity userEntity = mapper.convertDTOToEntity(userInputDTO);
        userEntity.setPassword(bCryptPasswordEncoder.encode(userInputDTO.getPassword()));

        userRepository.save(userEntity);

        return mapper.convertEntityToDTO(userEntity);
    }

    private void validateUserInputDTO(UserInputDTO dto) {
        if(isEmpty(dto.getUsername()) || isEmpty(dto.getPassword()) || isEmpty(dto.getProfiles())) {
            throw new DtoNullOrIsEmptyException(CodeMessage.DTO_NULL_OR_IS_EMPTY);
        }
    }
}
