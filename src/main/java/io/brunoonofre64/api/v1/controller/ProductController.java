package io.brunoonofre64.api.v1.controller;

import io.brunoonofre64.domain.dto.DataToCreateProductDTO;
import io.brunoonofre64.domain.dto.ProductDTO;
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
    public ProductDTO saveNewProductInDb(@RequestBody DataToCreateProductDTO dto) {
        return service.saveNewProductInDb(dto);
    }

    @GetMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO getProductByUuid(@PathVariable String uuid) {
        return service.getProductByUuid(uuid);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<ProductDTO> getAllProducts(Pageable pageable) {
        return service.getAllProducts(pageable);
    }

    @PutMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ProductDTO updateProductByUuid(@PathVariable String uuid,
                                          @RequestBody DataToCreateProductDTO dto) {
       return service.updateProductByUuid(uuid, dto);
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProductByUuid(@PathVariable String uuid) {
        service.deleteProductByUuid(uuid);
    }
}
