package io.brunoonofre64.infrastructure.jpa;

import io.brunoonofre64.domain.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    boolean existsByUuid(String uuid);

    @Query("SELECT C FROM OrderEntity C LEFT JOIN FETCH C.orderItems WHERE C.uuid = :uuid")
    OrderEntity findByUuidAndFetchOrderItems(@Param("uuid") String uuid);
}

