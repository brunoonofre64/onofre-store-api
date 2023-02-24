package br.com.onofrestore.domain.service;

import br.com.onofrestore.domain.dto.order.OrderInformationDTO;
import br.com.onofrestore.domain.dto.order.OrderInputDTO;
import br.com.onofrestore.domain.dto.order.OrderNewStatusDTO;
import br.com.onofrestore.domain.dto.order.OrderOutputDTO;
import org.springframework.data.domain.Page;

public interface OrderService {
    OrderOutputDTO saveNewOrderInDb(OrderInputDTO dto);
    Page<OrderInformationDTO> getAllOrderPaged();
    void deleteOrderByUuid(String uuid);
    OrderInformationDTO updateStatusOrderByUuid(String uuid, OrderNewStatusDTO dto);
}
