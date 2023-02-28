package br.com.onofrestore.domain.service;

import br.com.onofrestore.domain.dto.order.OrderInformationDTO;
import br.com.onofrestore.domain.dto.order.OrderNewStatusDTO;
import br.com.onofrestore.domain.dto.order.OrderOutputDTO;
import br.com.onofrestore.domain.dto.orderitems.OrderItemsInputDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderService {
    OrderOutputDTO saveNewOrderInDb(List<OrderItemsInputDTO> dto);
    OrderOutputDTO getOrderByUuid(String uuid);
    Page<OrderInformationDTO> getAllOrderPaged();
    void deleteOrderByUuid(String uuid);
    OrderInformationDTO updateStatusOrderByUuid(String uuid, OrderNewStatusDTO dto);
}
