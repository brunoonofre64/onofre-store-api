package io.brunoonofre64.infrastructure.jpa;

import io.brunoonofre64.domain.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    boolean existsByUuid(String uuid);

    OrderEntity findByUuid(String uuid);

    void deleteByUuid(String uuid);

}

