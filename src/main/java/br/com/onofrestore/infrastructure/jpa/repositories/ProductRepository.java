package br.com.onofrestore.infrastructure.jpa.repositories;

import br.com.onofrestore.domain.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<ProductEntity, Long>, JpaSpecificationExecutor<ProductEntity> {
    ProductEntity findByUuid(String uuid);
    void deleteByUuid(String uuid);
    boolean existsByProductName(String productName);
}





