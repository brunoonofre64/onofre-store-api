package io.brunoonofre64.api.v1.controller;

import io.brunoonofre64.domain.dto.OrderInputDTO;
import io.brunoonofre64.domain.dto.OrderItemsInformationDTO;
import io.brunoonofre64.domain.dto.OrderOutputDTO;
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
    public OrderItemsInformationDTO getOrderItemsInformationByUuid(@PathVariable String uuid) {
       return service.getOrderItemsInformationByUuid(uuid);
    }
}
