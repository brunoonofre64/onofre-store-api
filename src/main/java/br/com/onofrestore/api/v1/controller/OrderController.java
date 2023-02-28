package br.com.onofrestore.api.v1.controller;

import br.com.onofrestore.domain.dto.order.OrderInformationDTO;
import br.com.onofrestore.domain.dto.order.OrderNewStatusDTO;
import br.com.onofrestore.domain.dto.order.OrderOutputDTO;
import br.com.onofrestore.domain.dto.orderitems.OrderItemsInputDTO;
import br.com.onofrestore.domain.service.OrderService;
import br.com.onofrestore.infrastructure.config.security.CheckSecurity;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pedido")
@AllArgsConstructor
public class OrderController {

    private OrderService service;

    @CheckSecurity.Permit.CanSaveOrder
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderOutputDTO saveNewOrderInDb(@RequestBody List<OrderItemsInputDTO> dto) {
        return service.saveNewOrderInDb(dto);
    }

    @CheckSecurity.Permit.CanReadOrder
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<OrderInformationDTO> getAllOrderPaged() {
        return service.getAllOrderPaged();
    }

    @CheckSecurity.Permit.CanUpdateOrder
    @PatchMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public OrderInformationDTO updateStatusOfOrderByUuid(@PathVariable String uuid,
                                                    @RequestBody OrderNewStatusDTO dto) {
        return service.updateStatusOrderByUuid(uuid, dto);
    }

    @CheckSecurity.Permit.CanDeleteOrder
    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelOrderByUuid(@PathVariable String uuid) {
        service.deleteOrderByUuid(uuid);
    }
}
