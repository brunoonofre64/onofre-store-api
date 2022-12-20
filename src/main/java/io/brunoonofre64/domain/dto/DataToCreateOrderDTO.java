package io.brunoonofre64.domain.dto;

import io.brunoonofre64.domain.entities.CustomerEntity;
import io.brunoonofre64.domain.entities.ProductEntity;
import io.brunoonofre64.domain.enums.Status;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.PrePersist;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class DataToCreateOrderDTO {

    private String uuid;

    private Status status = Status.DONE;

    private LocalDateTime orderDate;

    private BigDecimal total;

    private CustomerEntity customerEntity;

    private List<ProductEntity> products;

    @PrePersist
    private void prePersist() {
        uuid = java.util.UUID.randomUUID().toString();
    }
}
