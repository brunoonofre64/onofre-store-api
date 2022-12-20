package io.brunoonofre64.domain.service;

import io.brunoonofre64.domain.dto.DataToCreateProductDTO;
import io.brunoonofre64.domain.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    ProductDTO saveNewProductInDb(DataToCreateProductDTO dto);

    ProductDTO getProductByUuid(String name);

    Page<ProductDTO> getAllProducts(Pageable pageable);

    ProductDTO updateProductByUuid(String uuid, DataToCreateProductDTO dto);

    void deleteProductByUuid(String uuid);
}
