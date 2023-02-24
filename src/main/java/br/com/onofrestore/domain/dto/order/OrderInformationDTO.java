package br.com.onofrestore.domain.dto.order;

import br.com.onofrestore.domain.dto.orderitems.OrderItemsInformationtDTO;
import br.com.onofrestore.domain.enums.Status;
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

   private String nameUser;

  private List<OrderItemsInformationtDTO> orderItems;
}
