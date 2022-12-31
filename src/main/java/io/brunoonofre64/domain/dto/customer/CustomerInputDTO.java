package io.brunoonofre64.domain.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CustomerInputDTO {

    private String name;

    private String age;

    @CPF
    private String cpf;
}
