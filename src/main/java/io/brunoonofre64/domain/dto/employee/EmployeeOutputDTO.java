package io.brunoonofre64.domain.dto.employee;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
@Builder
public class EmployeeOutputDTO {

    private String name;

    private String uuid;

    private String email;

    @CPF
    private String cpf;
}
