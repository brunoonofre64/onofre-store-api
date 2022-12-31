package io.brunoonofre64.domain.dto.order;

import io.brunoonofre64.domain.dto.customer.CustomerInformationDTO;
import io.brunoonofre64.domain.enums.Status;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class OrderOutputDTO {

    private String uuid;

    private Status status;

    private LocalDateTime orderDate;

    private BigDecimal total;

    private CustomerInformationDTO customer;
}
