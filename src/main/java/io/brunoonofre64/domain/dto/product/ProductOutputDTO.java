package io.brunoonofre64.domain.dto.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ProductOutputDTO {

    private String uuid;

    private String productName;

    private String description;

    private BigDecimal unitValue;

    private LocalDateTime inclusionDate;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDateTime modifyDate;
}
