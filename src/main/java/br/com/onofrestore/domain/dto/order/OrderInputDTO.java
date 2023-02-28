package br.com.onofrestore.domain.dto.order;

import br.com.onofrestore.domain.dto.orderitems.OrderItemsInputDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class OrderInputDTO {
    private List<OrderItemsInputDTO> orderItems;
}
