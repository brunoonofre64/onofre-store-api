package io.brunoonofre64.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ORDER_ITEMS")
@SequenceGenerator(name = "orderItemsSequence", sequenceName = "SQ_ORDER_ITEMS", allocationSize = 1)
public class OrderItemsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderItemsSequence")
    @Column(name = "ID")
    private Long id;

    @Column(name = "UUID", nullable = false, length = 36)
    private String uuid;

    @Column(name = "AMOUNT", nullable = false)
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private OrderEntity orderEntity;

    @PrePersist
    private void prePersist() {
        uuid = java.util.UUID.randomUUID().toString();
    }
}
