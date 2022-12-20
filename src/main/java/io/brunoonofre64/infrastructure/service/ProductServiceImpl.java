package io.brunoonofre64.infrastructure.service;

import io.brunoonofre64.domain.dto.DataToCreateProductDTO;
import io.brunoonofre64.domain.dto.ProductDTO;
import io.brunoonofre64.domain.entities.ProductEntity;
import io.brunoonofre64.domain.enums.CodeMessage;
import io.brunoonofre64.domain.exception.DtoNullOrIsEmptyException;
import io.brunoonofre64.domain.exception.ListIsEmptyException;
import io.brunoonofre64.domain.exception.ProductNotFoundInStock;
import io.brunoonofre64.domain.exception.UuidNotFoundOrNullException;
import io.brunoonofre64.domain.mapper.ProductMapper;
import io.brunoonofre64.domain.service.ProductService;
import io.brunoonofre64.infrastructure.jpa.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductRepository repository;

    private ProductMapper mapper;

    @Override
    public ProductDTO saveNewProductInDb(DataToCreateProductDTO dto) {
        if(validateIfDtoFieldIsNotNullOrEmpty(dto)) {
            throw new DtoNullOrIsEmptyException(CodeMessage.DTO_NULL_OR_IS_EMPTY);
        }
        ProductEntity entity = mapper.convertDTOToEntity(dto);

        repository.save(entity);

        return mapper.convertEntityToDTO(entity);
    }

    @Override
    public ProductDTO getProductByUuid(String uuid) {
        if(ObjectUtils.isEmpty(uuid) || !repository.existsByUuid(uuid)) {
            throw new ProductNotFoundInStock(CodeMessage.PRODUCT_NOT_FOUND);
        }
        ProductEntity entity = repository.findByUuid(uuid);

        return mapper.convertEntityToDTO(entity);
    }

    @Override
    public Page<ProductDTO> getAllProducts(Pageable pageable) {
        if(ObjectUtils.isEmpty(pageable) || pageable.isUnpaged()) {
            throw new ListIsEmptyException(CodeMessage.LIST_IS_EMPTY);
        }
        pageable = PageRequest.of(0,10);

        Page<ProductEntity> entity = repository.findAll(pageable);
        return mapper.mapPagesProductEntityToDTO(entity);
    }

    @Override
    public ProductDTO updateProductByUuid(String uuid, DataToCreateProductDTO dto) {
        if(ObjectUtils.isEmpty(uuid) || repository.existsByUuid(uuid)) {
            throw new UuidNotFoundOrNullException(CodeMessage.UUID_NOT_FOUND_OR_NULL);
        }
        if(validateIfDtoFieldIsNotNullOrEmpty(dto)) {
            throw new DtoNullOrIsEmptyException(CodeMessage.DTO_NULL_OR_IS_EMPTY);
        }
        ProductEntity entity = repository.findByUuid(uuid);
        entity.setProductName(dto.getProductName());
        entity.setDescription(dto.getDescription());
        entity.setUnitValue(dto.getUnitValue());

        repository.save(entity);

        return mapper.convertEntityToDTO(entity);
    }

    @Override
    @Transactional
    public void deleteProductByUuid(String uuid) {
        if(ObjectUtils.isEmpty(uuid) || repository.existsByUuid(uuid)) {
            throw new UuidNotFoundOrNullException(CodeMessage.UUID_NOT_FOUND_OR_NULL);
        }
        repository.deleteByUuid(uuid);
    }

   private boolean validateIfDtoFieldIsNotNullOrEmpty(DataToCreateProductDTO dto) {
        return ObjectUtils.isEmpty(dto.getProductName()) && ObjectUtils.isEmpty(dto.getDescription())
                && ObjectUtils.isEmpty(dto.getUnitValue());
   }
}
