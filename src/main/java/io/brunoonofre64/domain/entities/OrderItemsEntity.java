package io.brunoonofre64.domain.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TBL_ORDER_ITEMS")
@SequenceGenerator(name = "orderItemsSequence", sequenceName = "SQ_ORDER_ITEMS", allocationSize = 1)
public class OrderItemsEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderItemsSequence")
    @Column(name = "ID")
    private Long id;

    @Column(name = "AMOUNT", nullable = false)
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private OrderEntity orderEntity;

    public OrderItemsEntity(String uuid, LocalDateTime inclusionDate, LocalDateTime modifyDate, Long id,
                            BigDecimal amount, ProductEntity product, OrderEntity orderEntity) {
        super(uuid, inclusionDate, modifyDate);
        this.id = id;
        this.amount = amount;
        this.product = product;
        this.orderEntity = orderEntity;
    }
}
