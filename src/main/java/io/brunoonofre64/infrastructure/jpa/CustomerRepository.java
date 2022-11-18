package io.brunoonofre64.infrastructure.jpa;

import io.brunoonofre64.api.v1.dto.CustomerDTO;
import io.brunoonofre64.domain.entities.CustomerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomerRepository extends PagingAndSortingRepository<CustomerEntity, Long> {
    CustomerEntity findByUuid(String uuid);
    Page<CustomerDTO> findAllBy(Pageable pageable);
    void deleteByUuid(String uuid);
    boolean existsByUuid(String uuid);
}
