package io.brunoonofre64.api.v1.controller;

import io.brunoonofre64.domain.dto.product.ProductInputDTO;
import io.brunoonofre64.domain.dto.product.ProductOutputDTO;
import io.brunoonofre64.domain.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/produto")
@AllArgsConstructor
public class ProductController {

    private ProductService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductOutputDTO saveNewProductInDb(@RequestBody ProductInputDTO dto) {
        return service.saveNewProductInDb(dto);
    }

    @GetMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public ProductOutputDTO getProductByUuid(@PathVariable String uuid) {
        return service.getProductByUuid(uuid);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<ProductOutputDTO> getAllProducts(Pageable pageable) {
        return service.getAllProducts(pageable);
    }

    @PutMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ProductOutputDTO updateProductByUuid(@PathVariable String uuid,
                                                @RequestBody ProductInputDTO dto) {
       return service.updateProductByUuid(uuid, dto);
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProductByUuid(@PathVariable String uuid) {
        service.deleteProductByUuid(uuid);
    }
}
