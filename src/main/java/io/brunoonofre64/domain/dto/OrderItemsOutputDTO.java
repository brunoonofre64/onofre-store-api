package io.brunoonofre64.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderItemsOutputDTO {

    private String product;

    private BigDecimal amount;
}
