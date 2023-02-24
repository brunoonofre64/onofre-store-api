package br.com.onofrestore.infrastructure.service;

import br.com.onofrestore.domain.dto.product.ProductInputDTO;
import br.com.onofrestore.domain.dto.product.ProductOutputDTO;
import br.com.onofrestore.domain.entities.ProductEntity;
import br.com.onofrestore.domain.enums.CodeMessage;
import br.com.onofrestore.domain.exception.DtoNullOrIsEmptyException;
import br.com.onofrestore.domain.exception.ListIsEmptyException;
import br.com.onofrestore.domain.exception.UuidNotFoundOrNullException;
import br.com.onofrestore.domain.mapper.ProductMapper;
import br.com.onofrestore.domain.service.ProductService;
import br.com.onofrestore.infrastructure.jpa.repositories.ProductRepository;
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
    public ProductOutputDTO saveNewProductInDb(ProductInputDTO dto) {
        validateProduct(dto);

        ProductEntity entity = mapper.convertDTOToEntity(dto);

        repository.save(entity);

        return mapper.convertEntityToDTO(entity);
    }

    @Override
    public ProductOutputDTO getProductByUuid(String uuid) {
        validateProductUuid(uuid);
        ProductEntity entity = repository.findByUuid(uuid);

        return mapper.convertEntityToDTO(entity);
    }

    @Override
    public Page<ProductOutputDTO> getAllProducts(Pageable pageable) {
        if(ObjectUtils.isEmpty(pageable) || pageable.isUnpaged()) {
            throw new ListIsEmptyException(CodeMessage.LIST_IS_EMPTY);
        }
        pageable = PageRequest.of(0,10);

        Page<ProductEntity> entity = repository.findAll(pageable);
        return mapper.mapPagesProductEntityToDTO(entity);
    }

    @Override
    public ProductOutputDTO updateProductByUuid(String uuid, ProductInputDTO dto) {
        validateProduct(dto);
        validateProductUuid(uuid);

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
        validateProductUuid(uuid);

        repository.deleteByUuid(uuid);
    }

    private void validateProductUuid(String uuid) {
        if(ObjectUtils.isEmpty(uuid) || !repository.existsByUuid(uuid)) {
            throw new UuidNotFoundOrNullException(CodeMessage.PRODUCT_NOT_FOUND);
        }
    }

   private void validateProduct(ProductInputDTO dto) {
        if(ObjectUtils.isEmpty(dto.getProductName()) || ObjectUtils.isEmpty(dto.getDescription())
                || ObjectUtils.isEmpty(dto.getUnitValue())) {
            throw new DtoNullOrIsEmptyException(CodeMessage.DTO_NULL_OR_IS_EMPTY);
       }
   }
}
