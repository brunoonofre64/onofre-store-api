package io.brunoonofre64.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class OrderInputDTO {

    private String customer;

    private BigDecimal total;

    private List<OrderItemsInputDTO> orderItems;
}
