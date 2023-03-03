package br.com.onofrestore.domain.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TBL_ORDER_ITEMS")
@SequenceGenerator(name = "orderItemsSequence", sequenceName = "SQ_ORDER_ITEMS", allocationSize = 1)
public class OrderItemsEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderItemsSequence")
    @Column(name = "ID")
    private Long id;

    @Column(name = "AMOUNT", nullable = false)
    private Long amount;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private OrderEntity orderEntity;

}
