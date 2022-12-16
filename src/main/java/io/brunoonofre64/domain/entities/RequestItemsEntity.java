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
@Table(name = "REQUEST_ITEMS")
@SequenceGenerator(name = "requestItemsSequence", sequenceName = "SQ_requestItems", allocationSize = 1)
public class RequestItemsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "requestItemsSequence")
    @Column(name = "ID")
    private Long id;

    @Column(name = "UUID", nullable = false, length = 36)
    private String uuid;

    @Column(name = "AMOUNT", nullable = false)
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private ProductEntity productEntity;

    @ManyToOne
    @JoinColumn(name = "REQUEST_ID")
    private RequestEntity requestEntity;

    @PrePersist
    private void prePersist() {
        uuid = java.util.UUID.randomUUID().toString();
    }
}
