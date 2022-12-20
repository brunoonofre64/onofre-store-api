package io.brunoonofre64.domain.mapper;

import io.brunoonofre64.domain.dto.DataToCreateProductDTO;
import io.brunoonofre64.domain.dto.ProductDTO;
import io.brunoonofre64.domain.entities.ProductEntity;
import org.springframework.data.domain.Page;

public interface ProductMapper {

    ProductDTO convertEntityToDTO(ProductEntity entity);

    ProductEntity convertDTOToEntity(DataToCreateProductDTO dto);

    Page<ProductDTO> mapPagesProductEntityToDTO(Page<ProductEntity> entity);
}
