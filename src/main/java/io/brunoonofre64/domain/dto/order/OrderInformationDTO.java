package io.brunoonofre64.domain.dto.order;

import io.brunoonofre64.domain.dto.orderitems.OrderItemsInformationtDTO;
import io.brunoonofre64.domain.enums.Status;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderInformationDTO {

   private String uuidOrder;

   private Status status;

   private LocalDateTime orderDate;

   private BigDecimal total;

   private String cpf;

   private String nameCustomer;

  private List<OrderItemsInformationtDTO> orderItems;
}
