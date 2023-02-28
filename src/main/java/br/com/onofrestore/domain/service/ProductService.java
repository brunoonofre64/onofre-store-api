package br.com.onofrestore.domain.service;

import br.com.onofrestore.domain.dto.product.ProductInputDTO;
import br.com.onofrestore.domain.dto.product.ProductOutputDTO;
import br.com.onofrestore.domain.dto.util.SearchDTO;
import org.springframework.data.domain.Page;

public interface ProductService {
    ProductOutputDTO saveNewProductInDb(ProductInputDTO dto);
    ProductOutputDTO getProductByUuid(String name);
    Page<ProductOutputDTO> getAllProducts(SearchDTO dto);
    ProductOutputDTO updateProductByUuid(String uuid, ProductInputDTO dto);
    void deleteProductByUuid(String uuid);
}
