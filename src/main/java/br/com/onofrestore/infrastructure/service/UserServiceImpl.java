package br.com.onofrestore.infrastructure.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl  {

//    private UserRepository userRepository;
//    private PasswordEncoder passwordEncoder;
//    private UserMapper mapper;

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        if (!username.equals("bruno")) {
//            throw new UsernameNotFoundException("User not found: " + username);
//        }
//
//        return User.withUsername(username)
//                .password(passwordEncoder.encode("1234"))
//                .roles("ADMIN", "EMPLOYEE", "MANAGER")
//                .build();
//    }
//
//    public UserOutpuDTO saveNewUserInDb(UserInputDTO userInputDTO) {
//        this.validateUserInputDTO(userInputDTO);
//
//        UserEntity userEntity = mapper.convertDTOToEntity(userInputDTO);
//        userEntity.setPassword(passwordEncoder.encode(userInputDTO.getPassword()));
//
//        userRepository.save(userEntity);
//
//        return mapper.convertEntityToDTO(userEntity);
//    }
//
//    private void validateUserInputDTO(UserInputDTO dto) {
//        if(isEmpty(dto.getUsername()) || isEmpty(dto.getPassword()) || isEmpty(dto.getProfiles())) {
//            throw new DtoNullOrIsEmptyException(CodeMessage.DTO_NULL_OR_IS_EMPTY);
//        }
//    }
}
