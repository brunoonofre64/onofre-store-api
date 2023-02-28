package br.com.onofrestore.api.v1.controller;

import br.com.onofrestore.domain.dto.product.ProductInputDTO;
import br.com.onofrestore.domain.dto.product.ProductOutputDTO;
import br.com.onofrestore.domain.service.ProductService;
import br.com.onofrestore.infrastructure.config.security.CheckSecurity;
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

    @CheckSecurity.Permit.CanSave
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductOutputDTO saveNewProductInDb(@RequestBody ProductInputDTO dto) {
        return service.saveNewProductInDb(dto);
    }

    @CheckSecurity.Permit.CanAuthenticated
    @GetMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public ProductOutputDTO getProductByUuid(@PathVariable String uuid) {
        return service.getProductByUuid(uuid);
    }

    @CheckSecurity.Permit.CanAuthenticated
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<ProductOutputDTO> getAllProducts(Pageable pageable) {
        return service.getAllProducts(pageable);
    }

    @CheckSecurity.Permit.CanSave
    @PutMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ProductOutputDTO updateProductByUuid(@PathVariable String uuid,
                                                @RequestBody ProductInputDTO dto) {
       return service.updateProductByUuid(uuid, dto);
    }

    @CheckSecurity.Permit.CanDelete
    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProductByUuid(@PathVariable String uuid) {
        service.deleteProductByUuid(uuid);
    }
}
