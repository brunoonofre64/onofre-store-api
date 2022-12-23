package io.brunoonofre64.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class OrderItemsOutputDTO {

    private String product;

    private BigDecimal amount;
}
