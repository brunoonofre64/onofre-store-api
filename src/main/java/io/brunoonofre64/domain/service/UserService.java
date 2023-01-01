package io.brunoonofre64.domain.service;

import io.brunoonofre64.domain.dto.UserInputDTO;
import io.brunoonofre64.domain.dto.UserOutpuDTO;

public interface UserService {

    UserOutpuDTO saveNewUserIndDB(UserInputDTO userDTO);

    UserOutpuDTO getUserByUuid(String uuid);

    UserOutpuDTO updateUserByUui(String uuid, UserInputDTO dto);

    void deleteUserByUuid(String uuid);
}
