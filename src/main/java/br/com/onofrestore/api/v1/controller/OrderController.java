package br.com.onofrestore.api.v1.controller;

import br.com.onofrestore.domain.dto.order.OrderInformationDTO;
import br.com.onofrestore.domain.dto.order.OrderInputDTO;
import br.com.onofrestore.domain.dto.order.OrderNewStatusDTO;
import br.com.onofrestore.domain.dto.order.OrderOutputDTO;
import br.com.onofrestore.domain.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pedido")
@AllArgsConstructor
public class OrderController {

    private OrderService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderOutputDTO saveNewOrderInDb(@RequestBody OrderInputDTO dto) {
        return service.saveNewOrderInDb(dto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<OrderInformationDTO> getAllOrderPaged() {
        return service.getAllOrderPaged();
    }

    @PatchMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public OrderInformationDTO updateStatusOfOrderByUuid(@PathVariable String uuid,
                                                    @RequestBody OrderNewStatusDTO dto) {
        return service.updateStatusOrderByUuid(uuid, dto);
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelOrderByUuid(@PathVariable String uuid) {
        service.deleteOrderByUuid(uuid);
    }
}
