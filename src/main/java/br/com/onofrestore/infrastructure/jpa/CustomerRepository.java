package br.com.onofrestore.infrastructure.jpa;

import br.com.onofrestore.domain.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    CustomerEntity findByUuid(String uuid);
    void deleteByUuid(String uuid);
    boolean existsByUuid(String uuid);
    boolean existsByCpf(String cpf);
}

