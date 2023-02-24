package br.com.onofrestore.domain.dto.user;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserInputDTO {
    private String username;
    private String password;
    private String email;
    private String fullName;
    private Set<String> uuidProfiles;
}
