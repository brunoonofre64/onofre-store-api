package io.brunoonofre64.api.v1.controller;

import io.brunoonofre64.domain.dto.OrderInputDTO;
import io.brunoonofre64.domain.entities.OrderEntity;
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
    public Long saveNewOrderInDb(@RequestBody OrderInputDTO dto) {
        OrderEntity order =  service.saveNewOrderInDb(dto);
        return order.getId();
    }
}
