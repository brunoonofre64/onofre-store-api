package io.brunoonofre64.infrastructure.jpa;

import io.brunoonofre64.domain.entities.ProductEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<ProductEntity, Long> {

    boolean existsByUuid(String uuid);

    ProductEntity findByUuid(String uuid);

    void deleteByUuid(String uuid);
}





