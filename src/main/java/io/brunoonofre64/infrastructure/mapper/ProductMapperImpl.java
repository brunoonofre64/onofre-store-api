package io.brunoonofre64.infrastructure.mapper;

import io.brunoonofre64.domain.dto.DataToCreateProductDTO;
import io.brunoonofre64.domain.dto.ProductDTO;
import io.brunoonofre64.domain.entities.ProductEntity;
import io.brunoonofre64.domain.enums.CodeMessage;
import io.brunoonofre64.domain.exception.ListIsEmptyException;
import io.brunoonofre64.domain.mapper.ProductMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductDTO convertEntityToDTO(ProductEntity entity) {
        ProductDTO dto = new ProductDTO();
        dto.setUuid(entity.getUuid());
        dto.setProductName(entity.getProductName());
        dto.setDescription(entity.getDescription());
        dto.setInclusionDate(entity.getInclusionDate());
        dto.setModifyDate(entity.getModifyDate());
        dto.setUnitValue(entity.getUnitValue());

        return dto;
    }

    @Override
    public ProductEntity convertDTOToEntity(DataToCreateProductDTO dto) {
        ProductEntity entity = new ProductEntity();
        entity.setProductName(dto.getProductName());
        entity.setDescription(dto.getDescription());
        entity.setUnitValue(dto.getUnitValue());

        return entity;
    }

    @Override
        public Page<ProductDTO> mapPagesProductEntityToDTO(Page<ProductEntity> entity) {
            if(ObjectUtils.isEmpty(entity)) {
                throw new ListIsEmptyException(CodeMessage.LIST_IS_EMPTY);
            }
            return entity.map(this::convertEntityToDTO);
        }
    }

