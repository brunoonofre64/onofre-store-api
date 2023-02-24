package br.com.onofrestore.api.v1.controller;

import br.com.onofrestore.domain.dto.user.ChangePasswordDTO;
import br.com.onofrestore.domain.dto.user.UserInputDTO;
import br.com.onofrestore.domain.dto.user.UserOutpuDTO;
import br.com.onofrestore.domain.dto.util.SearchDTO;
import br.com.onofrestore.infrastructure.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/usuario")
public class UserController {

    private final UserServiceImpl service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserOutpuDTO saveNewUserInDb(@RequestBody UserInputDTO userInputDTO) {
        return service.saveNewUserInDb(userInputDTO);
    }

    @PutMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changePassword(@PathVariable String uuid, @RequestBody ChangePasswordDTO dto) {
        service.changePassword(uuid, dto);
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserByUuid(@PathVariable String uuid) {
        service.deleteUserByUuid(uuid);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public UserOutpuDTO findByUsername(@RequestParam String username) {
        return service.findByUsername(username);
    }

    @GetMapping("/paginado")
    @ResponseStatus(HttpStatus.OK)
    public Page<UserOutpuDTO> getAllUserPaged(@ModelAttribute SearchDTO dto) {
        return service.getAllUserPaged(dto);
    }
}
