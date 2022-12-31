package io.brunoonofre64.infrastructure.mapper;

import io.brunoonofre64.domain.dto.product.ProductInputDTO;
import io.brunoonofre64.domain.dto.product.ProductOutputDTO;
import io.brunoonofre64.domain.entities.ProductEntity;
import io.brunoonofre64.domain.enums.CodeMessage;
import io.brunoonofre64.domain.exception.BusinessRuleException;
import io.brunoonofre64.domain.exception.ListIsEmptyException;
import io.brunoonofre64.domain.mapper.ProductMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductOutputDTO convertEntityToDTO(ProductEntity entity) {
        if(entity == null) {
            throw new BusinessRuleException(CodeMessage.ENTITY_ISNULL);
        }
        return ProductOutputDTO
                .builder()
                .uuid(entity.getUuid())
                .productName(entity.getProductName())
                .description(entity.getDescription())
                .inclusionDate(entity.getInclusionDate())
                .modifyDate(entity.getModifyDate())
                .unitValue(entity.getUnitValue())
                .build();
    }

    @Override
    public ProductEntity convertDTOToEntity(ProductInputDTO dto) {
        if(dto == null) {
            throw new BusinessRuleException(CodeMessage.OBJECTS_ISNULL_OR_EMPTY);
        }
        return ProductEntity
                .builder()
                .productName(dto.getProductName())
                .description(dto.getDescription())
                .unitValue(dto.getUnitValue())
                .build();
    }

    @Override
        public Page<ProductOutputDTO> mapPagesProductEntityToDTO(Page<ProductEntity> entity) {
            if(ObjectUtils.isEmpty(entity)) {
                throw new ListIsEmptyException(CodeMessage.LIST_IS_EMPTY);
            }
            return entity.map(this::convertEntityToDTO);
        }
    }

