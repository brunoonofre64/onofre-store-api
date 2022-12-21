package io.brunoonofre64.domain.dto;

import io.brunoonofre64.domain.entities.CustomerEntity;
import io.brunoonofre64.domain.entities.ProductEntity;
import io.brunoonofre64.domain.enums.Status;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrderOutputDTO {

    private String uuid;

    private Status status;

    private LocalDateTime orderDate;

    private BigDecimal total;

    private CustomerEntity customerEntity;

    private List<ProductEntity> products;
}
