package io.brunoonofre64.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderItemsInformationDTO {
   private String uuidProduct;

   private String productName;

   private BigDecimal unitValeu;
}
