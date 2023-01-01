package io.brunoonofre64.api.v1.controller;

import io.brunoonofre64.domain.dto.order.OrderInputDTO;
import io.brunoonofre64.domain.dto.order.OrderInformationDTO;
import io.brunoonofre64.domain.dto.order.OrderNewStatusDTO;
import io.brunoonofre64.domain.dto.order.OrderOutputDTO;
import io.brunoonofre64.domain.service.OrderService;
import lombok.AllArgsConstructor;
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

    @GetMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public OrderInformationDTO getOrderItemsInformationByUuid(@PathVariable String uuid) {
       return service.getOrderItemsInformationByUuid(uuid);
    }

    @PatchMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public OrderInformationDTO updateStatusOfOrderByUuid(@PathVariable String uuid,
                                                    @RequestBody OrderNewStatusDTO dto) {
        return service.updateStatusOfOrderByUuid(uuid, dto);
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelOrderByUuid(@PathVariable String uuid) {
        service.cancelOrderByUuid(uuid);
    }
}
