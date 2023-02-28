package br.com.onofrestore.infrastructure.service;

import br.com.onofrestore.domain.dto.product.ProductInputDTO;
import br.com.onofrestore.domain.dto.product.ProductOutputDTO;
import br.com.onofrestore.domain.dto.util.SearchDTO;
import br.com.onofrestore.domain.entities.ProductEntity;
import br.com.onofrestore.domain.enums.CodeMessage;
import br.com.onofrestore.domain.exception.*;
import br.com.onofrestore.domain.mapper.ProductMapper;
import br.com.onofrestore.domain.service.ProductService;
import br.com.onofrestore.infrastructure.jpa.repositories.ProductRepository;
import br.com.onofrestore.infrastructure.jpa.specifications.ProductSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductRepository repository;
    private ProductMapper mapper;
    private ProductSpecification productSpecification;

    @Override
    public ProductOutputDTO saveNewProductInDb(ProductInputDTO dto) {
        this.validateProduct(dto);
        this.mapperToLowerCase(dto);

        if (repository.existsByProductName(dto.getProductName())) {
            throw new ProductAlreadyExists(CodeMessage.PRODUCT_REPETEAD);
        }

        ProductEntity entity = mapper.convertDTOToEntity(dto);

        repository.save(entity);

        return mapper.convertEntityToDTO(entity);
    }

    @Override
    public ProductOutputDTO getProductByUuid(String uuid) {
        if (uuid == null) {
            throw new UuidNotFoundOrNullException(CodeMessage.UUID_NOT_FOUND_OR_NULL);
        }

        ProductEntity entity = repository.findByUuid(uuid);

        if (entity == null) {
            throw new ProductNotFoundException(CodeMessage.PRODUCT_NOT_FOUND);
        }

        return mapper.convertEntityToDTO(entity);
    }

    @Override
    public Page<ProductOutputDTO> getAllProducts(SearchDTO dto) {
        Pageable pageable = this.getSortedByInclusionDate(dto);

        Specification<ProductEntity> specification = productSpecification.byFilter(dto);
        Page<ProductEntity> entity = repository.findAll(specification, pageable);

        if (entity.isEmpty()) {
            throw new ListIsEmptyException(CodeMessage.LIST_IS_EMPTY);
        }
        return mapper.mapPagesProductEntityToDTO(entity);
    }

    @Override
    public ProductOutputDTO updateProductByUuid(String uuid, ProductInputDTO dto) {
        if (dto == null) {
            throw new DtoNullOrIsEmptyException(CodeMessage.DTO_NULL_OR_IS_EMPTY);
        }
        if (uuid == null) {
            throw new UuidNotFoundOrNullException(CodeMessage.UUID_NOT_FOUND_OR_NULL);
        }
        this.mapperToLowerCase(dto);

        ProductEntity entity = repository.findByUuid(uuid);

        if (entity == null) {
            throw new ProductNotFoundException(CodeMessage.PRODUCT_NOT_FOUND);
        }

        if (dto.getProductName() != null) {entity.setProductName(dto.getProductName());}
        if (dto.getDescription() != null){entity.setDescription(dto.getDescription());}
        if (dto.getUnitValue() != null){entity.setUnitValue(dto.getUnitValue());}

        repository.save(entity);

        return mapper.convertEntityToDTO(entity);
    }

    @Override
    @Transactional
    public void deleteProductByUuid(String uuid) {
        if (uuid == null) {
            throw new UuidNotFoundOrNullException(CodeMessage.UUID_NOT_FOUND_OR_NULL);
        }

        try {
            repository.deleteByUuid(uuid);
        } catch (Exception ex) {
            throw new ProductNotFoundException(CodeMessage.PRODUCT_NOT_FOUND);
        }
    }

   private void validateProduct(ProductInputDTO dto) {
        if (isEmpty(dto.getProductName()) || isEmpty(dto.getDescription())
                || isEmpty(dto.getUnitValue())) {
            throw new DtoNullOrIsEmptyException(CodeMessage.DTO_NULL_OR_IS_EMPTY);
       }
   }

    private Pageable getSortedByInclusionDate(SearchDTO dto) {
        return PageRequest.of(dto.getPage(), dto.getSize(),
                Sort.by("inclusionDate").descending());
    }

    private void mapperToLowerCase(ProductInputDTO dto) {
        if (!isEmpty(dto.getDescription())) {
            dto.setDescription(dto.getDescription().toLowerCase());
        }
        if (!isEmpty(dto.getProductName())) {
            dto.setProductName(dto.getProductName().toLowerCase());
        }
    }
}
