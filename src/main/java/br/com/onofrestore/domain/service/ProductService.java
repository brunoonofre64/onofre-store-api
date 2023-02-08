package br.com.onofrestore.domain.service;

import br.com.onofrestore.domain.dto.product.ProductInputDTO;
import br.com.onofrestore.domain.dto.product.ProductOutputDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    ProductOutputDTO saveNewProductInDb(ProductInputDTO dto);

    ProductOutputDTO getProductByUuid(String name);

    Page<ProductOutputDTO> getAllProducts(Pageable pageable);

    ProductOutputDTO updateProductByUuid(String uuid, ProductInputDTO dto);

    void deleteProductByUuid(String uuid);
}
