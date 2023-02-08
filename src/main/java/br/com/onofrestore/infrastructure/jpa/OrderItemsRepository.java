package br.com.onofrestore.infrastructure.jpa;

import br.com.onofrestore.domain.entities.OrderItemsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemsRepository extends JpaRepository<OrderItemsEntity, Long> {
    boolean existsByUuid(String uuid);

    OrderItemsEntity findByUuid(String uuid);
}

