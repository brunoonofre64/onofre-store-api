package io.brunoonofre64.infrastructure.jpa;

import io.brunoonofre64.domain.entities.OrderItemsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemsRepository extends JpaRepository<OrderItemsEntity, Long> {
}
