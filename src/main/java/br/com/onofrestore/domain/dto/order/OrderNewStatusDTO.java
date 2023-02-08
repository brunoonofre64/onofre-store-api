package br.com.onofrestore.domain.dto.order;

import br.com.onofrestore.domain.enums.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderNewStatusDTO {

    private Status status;
}


