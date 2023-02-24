package br.com.onofrestore.domain.dto.user;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Set;

@Getter
@Setter
public class UserInputDTO {
    private String email;
    private String username;
    @CPF(message = "CPF_INVALID_FORMAT")
    private String cpf;
    private String age;
    private String fullName;
    private String password;
    private Set<String> uuidProfiles;
}
