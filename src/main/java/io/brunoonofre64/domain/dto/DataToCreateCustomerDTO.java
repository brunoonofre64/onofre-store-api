package io.brunoonofre64.domain.dto;

import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DataToCreateCustomerDTO {

    private String name;

    private String age;

    @CPF
    private String cpf;
}
