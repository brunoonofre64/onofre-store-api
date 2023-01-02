package io.brunoonofre64.api.v1.controller;

import io.brunoonofre64.domain.dto.UserInputDTO;
import io.brunoonofre64.domain.dto.UserOutpuDTO;
import io.brunoonofre64.domain.service.UserService;
import lombok.AllArgsConstructor;
<<<<<<< HEAD
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
=======
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
>>>>>>> feature/crud-user

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/usuario")
public class UserController {

    private UserService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserOutpuDTO saveNewUserIndDB(UserInputDTO userDTO) {
        return service.saveNewUserIndDB(userDTO);
    }

<<<<<<< HEAD
=======
    @GetMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public UserOutpuDTO getCustomerByUuid(@PathVariable String uuid) {
        return service.getUserByUuid(uuid);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<UserOutpuDTO> getAllUserPaged(Pageable pageable) {
       return service.getAllUserPaged(pageable);
    }

    @PutMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public UserOutpuDTO updateUserByUui(@PathVariable String uuid,
                                        @RequestBody UserInputDTO dto) {
        return service.updateUserByUui(uuid, dto);
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserByUuid(@PathVariable String uuid) {
        service.deleteUserByUuid(uuid);
    }
>>>>>>> feature/crud-user

}
