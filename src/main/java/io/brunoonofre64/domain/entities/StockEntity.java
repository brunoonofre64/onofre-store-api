package io.brunoonofre64.domain.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "TBL_STOCK")
@SequenceGenerator(name = "sequenceStock", sequenceName = "SQ_STOCK", allocationSize = 1)
public class StockEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceStock")
    @Column(name = "ID")
    private Long id;

    @Column(name = "UUID", nullable = false, length = 36)
    private String uuid;

    @Column(name = "AVAILABLE_CAPCITY", nullable = false)
    private BigDecimal availableCapacity;

    @OneToMany(mappedBy = "stockEntity")
    private List<ProductEntity> products;

    @PrePersist
    private void prePersist() {
        uuid = java.util.UUID.randomUUID().toString();
    }
}
