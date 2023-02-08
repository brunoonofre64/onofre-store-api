package br.com.onofrestore.domain.mapper;

import br.com.onofrestore.domain.entities.ProductEntity;
import br.com.onofrestore.domain.dto.product.ProductInputDTO;
import br.com.onofrestore.domain.dto.product.ProductOutputDTO;
import org.springframework.data.domain.Page;

public interface ProductMapper {

    ProductOutputDTO convertEntityToDTO(ProductEntity entity);

    ProductEntity convertDTOToEntity(ProductInputDTO dto);

    Page<ProductOutputDTO> mapPagesProductEntityToDTO(Page<ProductEntity> entity);
}
