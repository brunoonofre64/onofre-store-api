package br.com.onofrestore.domain.dto.customer;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CustomerInformationDTO {

    private String name;

    private String cpf;
}
