package io.brunoonofre64.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DataToCreateProductDTO {
    private String productName;

    private String description;

    private BigDecimal unitValue;
}
