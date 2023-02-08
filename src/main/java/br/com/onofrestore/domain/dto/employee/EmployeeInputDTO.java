package br.com.onofrestore.domain.dto.employee;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
public class EmployeeInputDTO {
    private String name;
    @CPF
    private String cpf;
    private String email;
}
