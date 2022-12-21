package io.brunoonofre64.domain.service;

import io.brunoonofre64.domain.dto.ProductInputDTO;
import io.brunoonofre64.domain.dto.ProductOutputDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    ProductOutputDTO saveNewProductInDb(ProductInputDTO dto);

    ProductOutputDTO getProductByUuid(String name);

    Page<ProductOutputDTO> getAllProducts(Pageable pageable);

    ProductOutputDTO updateProductByUuid(String uuid, ProductInputDTO dto);

    void deleteProductByUuid(String uuid);
}
