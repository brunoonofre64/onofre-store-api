package io.brunoonofre64.api.v1.controller;

import io.brunoonofre64.domain.dto.user.UserInputDTO;
import io.brunoonofre64.domain.dto.user.UserOutpuDTO;
import io.brunoonofre64.infrastructure.service.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/usuario")
public class UserController {

    private UserServiceImpl service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserOutpuDTO saveNewUserIndDB(@RequestBody UserInputDTO userInputDTO) {
        return service.saveNewUserInDb(userInputDTO);
    }
}
