package br.com.onofrestore.domain.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TBL_PRODUCT")
@SequenceGenerator(name = "sequenceProduct", sequenceName = "SQ_PRODUCT", allocationSize = 1)
public class ProductEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceProduct" )
    @Column(name = "ID")
    private Long id;

    @Column(name = "PRODUCT_NAME", nullable = false, length = 100)
    private String productName;

    @Column(name = "DESCRIPTION", nullable = false, length = 150)
    private String description;

    @Column(name = "UNIT_VALUE", nullable = false)
    private BigDecimal unitValue;

}
