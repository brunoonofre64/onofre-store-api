package io.brunoonofre64.domain.dto.orderitems;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemsInformationtDTO {

    private String productName;

    private String description;

    private BigDecimal unitValue;

    private BigDecimal amount;
}
