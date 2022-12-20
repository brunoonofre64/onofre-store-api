package io.brunoonofre64.api.v1.controller;

import io.brunoonofre64.domain.dto.DataToCreateOrderDTO;
import io.brunoonofre64.domain.dto.OrderDTO;
import io.brunoonofre64.domain.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pedido")
@AllArgsConstructor
public class OrderController {

    private OrderService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDTO saveNewOrderInDb(@RequestBody DataToCreateOrderDTO dto) {
        return service.saveNewOrderInDb(dto);
    }

    @GetMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public OrderDTO getOrderByUuid(@PathVariable String uuid) {
        return service.getOrderByUuid(uuid);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDTO> getAllOrderTheseCustomer() {
        return service.getAllOrderTheseCustomer();
    }

    @PutMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public OrderDTO updateOrderByUuidAndNewItems(@PathVariable String uuid,
                                                 @RequestBody DataToCreateOrderDTO dto) {
        return service.updateOrderByUuidAndNewItems(uuid, dto);
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleterOrderByUuid(@PathVariable String uuid) {
        service.deleteOrderByUuid(uuid);
    }
}
