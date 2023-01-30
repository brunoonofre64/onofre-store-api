package io.brunoonofre64.infrastructure.service;

import io.brunoonofre64.domain.dto.user.UserInputDTO;
import io.brunoonofre64.domain.dto.user.UserOutpuDTO;
import io.brunoonofre64.domain.entities.UserEntity;
import io.brunoonofre64.domain.enums.CodeMessage;
import io.brunoonofre64.domain.exception.DtoNullOrIsEmptyException;
import io.brunoonofre64.domain.mapper.UserMapper;
import io.brunoonofre64.infrastructure.jpa.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserDetailsService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private UserMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!username.equals("bruno")) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        return User.withUsername(username)
                .password(passwordEncoder.encode("1234"))
                .roles("USER")
                .build();
    }

    public UserOutpuDTO saveNewUserInDb(UserInputDTO userInputDTO) {
        this.validateUserInputDTO(userInputDTO);

        UserEntity userEntity = mapper.convertDTOToEntity(userInputDTO);
        userEntity.setPassword(passwordEncoder.encode(userInputDTO.getPassword()));

        userRepository.save(userEntity);

        return mapper.convertEntityToDTO(userEntity);
    }

    private void validateUserInputDTO(UserInputDTO dto) {
        if(isEmpty(dto.getUsername()) || isEmpty(dto.getPassword()) || isEmpty(dto.getProfiles())) {
            throw new DtoNullOrIsEmptyException(CodeMessage.DTO_NULL_OR_IS_EMPTY);
        }
    }
}
