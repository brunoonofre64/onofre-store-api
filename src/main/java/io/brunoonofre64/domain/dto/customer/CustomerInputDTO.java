package io.brunoonofre64.domain.dto.customer;

import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerInputDTO {

    private String name;

    private String age;

    @CPF
    private String cpf;
}
