package io.brunoonofre64.domain.dto.orderitems;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class OrderItemsInputDTO {

    private String uuidProduct;

    private BigDecimal amount;
}
