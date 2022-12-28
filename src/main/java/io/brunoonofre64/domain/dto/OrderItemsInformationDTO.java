package io.brunoonofre64.domain.dto;

import io.brunoonofre64.domain.enums.Status;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrderItemsInformationDTO {

   private String uuidOrder;

   private Status status;

   private LocalDateTime orderDate;

   private BigDecimal total;

   private String customer;

  private List<OrderItemsOutputDTO> orderItems;
}
