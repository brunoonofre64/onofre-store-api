package io.brunoonofre64.api.v1.controller;

import io.brunoonofre64.domain.dto.user.UserInputDTO;
import io.brunoonofre64.domain.dto.user.UserOutpuDTO;
import io.brunoonofre64.infrastructure.service.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/usuario")
public class UserController {

    private UserServiceImpl service;
    private PasswordEncoder passwordEncoder;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserOutpuDTO saveNewUserIndDB(@RequestBody UserInputDTO userInputDTO) {
        return service.saveNewUserInDb(userInputDTO);
    }

    @GetMapping("/login")
    public ResponseEntity<String> login(
            @RequestHeader(value = "Authorization") String authorization) {
        String base64Credentials = authorization.substring("Basic".length()).trim();
        byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
        String credentials = new String(credDecoded, StandardCharsets.UTF_8);

        final String[] values = credentials.split(":", 2);

        UserDetails user = service.loadUserByUsername(values[0]);

        if (!passwordEncoder.matches(values[1], user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }

        return ResponseEntity.ok("Authorized");
    }
}
