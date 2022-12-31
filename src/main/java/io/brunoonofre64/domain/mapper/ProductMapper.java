package io.brunoonofre64.domain.mapper;

import io.brunoonofre64.domain.dto.product.ProductInputDTO;
import io.brunoonofre64.domain.dto.product.ProductOutputDTO;
import io.brunoonofre64.domain.entities.ProductEntity;
import org.springframework.data.domain.Page;

public interface ProductMapper {

    ProductOutputDTO convertEntityToDTO(ProductEntity entity);

    ProductEntity convertDTOToEntity(ProductInputDTO dto);

    Page<ProductOutputDTO> mapPagesProductEntityToDTO(Page<ProductEntity> entity);
}
