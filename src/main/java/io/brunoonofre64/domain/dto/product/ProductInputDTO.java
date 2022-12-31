package io.brunoonofre64.domain.dto.product;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductInputDTO {
    private String productName;

    private String description;

    private BigDecimal unitValue;
}
