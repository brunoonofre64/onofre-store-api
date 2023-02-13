package br.com.onofrestore.infrastructure.jpa;

import br.com.onofrestore.domain.entities.ProductEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<ProductEntity, Long> {
    boolean existsByUuid(String uuid);
    ProductEntity findByUuid(String uuid);
    void deleteByUuid(String uuid);
}





