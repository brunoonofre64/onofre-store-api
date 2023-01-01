package io.brunoonofre64.api.v1.controller;

import io.brunoonofre64.domain.dto.UserInputDTO;
import io.brunoonofre64.domain.dto.UserOutpuDTO;
import io.brunoonofre64.domain.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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


}
