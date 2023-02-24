package br.com.onofrestore.infrastructure.jpa.repositories;

import br.com.onofrestore.domain.entities.EmployeeEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<EmployeeEntity, Long> {
    EmployeeEntity findByUuid(String uuid);
    boolean existsByUuid(String uuid);
    void deleteByUuid(String uuid);
}
