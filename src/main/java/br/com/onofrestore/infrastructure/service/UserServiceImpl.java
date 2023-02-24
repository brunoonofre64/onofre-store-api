package br.com.onofrestore.infrastructure.service;

import br.com.onofrestore.domain.dto.user.ChangePasswordDTO;
import br.com.onofrestore.domain.dto.user.UserInputDTO;
import br.com.onofrestore.domain.dto.user.UserOutpuDTO;
import br.com.onofrestore.domain.dto.util.SearchDTO;
import br.com.onofrestore.domain.entities.RoleEntity;
import br.com.onofrestore.domain.entities.UserEntity;
import br.com.onofrestore.domain.enums.CodeMessage;
import br.com.onofrestore.domain.exception.*;
import br.com.onofrestore.domain.mapper.UserMapper;
import br.com.onofrestore.infrastructure.jpa.repositories.RoleRepository;
import br.com.onofrestore.infrastructure.jpa.repositories.UserRepository;
import br.com.onofrestore.infrastructure.jpa.specifications.UserSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.transaction.Transactional;
import java.util.Set;

import static org.springframework.util.CollectionUtils.isEmpty;
import static org.springframework.util.ObjectUtils.isEmpty;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserSpecification userSpecification;
    private final UserMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Email do login nao encontrado."));

        return User.withUsername(email).password(passwordEncoder.encode(userEntity.getPassword())).build();
    }

    @Transactional
    public UserOutpuDTO saveNewUserInDb(UserInputDTO dto) {
        this.validateUserInputDTO(dto);

        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new EmailAlreadyExistsException(CodeMessage.EMAIL_JA_REGISTRADO);
        }
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new UserAlreadyExistsException(CodeMessage.USUARIO_JA_REGISTRADO);
        }

        Set<String> uuidRoles = dto.getUuidProfiles();
        Set<RoleEntity> roles = roleRepository.findByUuidIn(uuidRoles);

        if (isEmpty(roles)) {
            throw new ListIsEmptyException(CodeMessage.LIST_IS_EMPTY);
        }

        UserEntity userEntity = mapper.convertDTOToEntity(dto, roles);
        userEntity.setPassword(passwordEncoder.encode(dto.getPassword()));

        userRepository.save(userEntity);

        return mapper.convertEntityToDTO(userEntity);
    }

    @Transactional
    public void changePassword(String uuid, ChangePasswordDTO dto) {
        if (dto == null || isEmpty(dto.getNewPassword()) || isEmpty(dto.getCurrentPassword())) {
            throw new NewPasswordException(CodeMessage.SENHA_INVALIDA);
        }

        UserEntity userEntity = userRepository.findByUuid(uuid);

        if (userEntity == null) {
            throw new UserNotFoundException(CodeMessage.USUARIO_NAO_ENCONTRADO);
        }
        if (!passwordEncoder.matches(dto.getCurrentPassword(), userEntity.getPassword())) {
            throw new IncorrectCurrentPasswordException(CodeMessage.SENHA_ATUAL_INCORRETA);
        }

        userEntity.setPassword(passwordEncoder.encode(dto.getNewPassword()));
        userRepository.save(userEntity);
    }

    @Transactional
    public void deleteUserByUuid(String uuid) {
        if (isEmpty(uuid)) {
            throw new UuidNotFoundOrNullException(CodeMessage.UUID_NOT_FOUND_OR_NULL);
        }

        try {
            userRepository.deleteByUuid(uuid);

        } catch (Exception ex) {
            throw new UserNotFoundException(CodeMessage.USER_NOTFOUND);
        }
    }

    public UserOutpuDTO findByUsername(String username) {
        if (isEmpty(username)) {
            throw new UserNotFoundException(CodeMessage.USER_NOTFOUND);
        }
        UserEntity user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UserNotFoundException(CodeMessage.USER_NOTFOUND);
        }

        return mapper.convertEntityToDTO(user);
    }

    public Page<UserOutpuDTO> getAllUserPaged(@ModelAttribute SearchDTO dto) {
        if (isEmpty(dto)) {
            throw new UserNotFoundException(CodeMessage.USER_NOTFOUND);
        }
        Pageable pageable = this.getSortedByInclusionDate(dto);

        Specification<UserEntity> specification = userSpecification.byFilter(dto);
        Page<UserEntity> userPaged = userRepository.findAll(specification, pageable);

        if (userPaged.isEmpty()) {
            throw new UserNotFoundException(CodeMessage.USER_NOTFOUND);
        }

        return mapper.mapPagesUserEntityToDTO(userPaged);
    }

    private Pageable getSortedByInclusionDate(SearchDTO dto) {
        return PageRequest.of(dto.getPage(), dto.getSize(),
                Sort.by("inclusionDate").descending());
    }

    private void validateUserInputDTO(UserInputDTO dto) {
        if (isEmpty(dto.getUsername()) || isEmpty(dto.getPassword()) || isEmpty(dto.getUuidProfiles())
        || isEmpty(dto.getAge()) || isEmpty(dto.getEmail()) || isEmpty(dto.getFullName())) {
            throw new DtoNullOrIsEmptyException(CodeMessage.DTO_NULL_OR_IS_EMPTY);
        }
    }
}
