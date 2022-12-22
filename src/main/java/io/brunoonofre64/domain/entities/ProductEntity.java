package io.brunoonofre64.domain.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "TBL_PRODUCT")
@SequenceGenerator(name = "sequenceProduct", sequenceName = "SQ_PRODUCT", allocationSize = 1)
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceProduct" )
    @Column(name = "ID")
    private Long id;

    @Column(name = "UUID", nullable = false, length = 36)
    private String uuid;

    @Column(name = "PRODUCT_NAME", nullable = false, length = 100)
    private String productName;

    @Column(name = "DESCRIPTION", nullable = false, length = 150)
    private String description;

    @Column(name = "UNIT_VALUE", nullable = false)
    private BigDecimal unitValue;

    @Column(name = "INC_DATE", nullable = false)
    private LocalDateTime inclusionDate;

    @Column(name = "MODF_DATE")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDateTime modifyDate;

    @PrePersist
    private void prePersist() {
        inclusionDate = LocalDateTime.now();
        uuid = java.util.UUID.randomUUID().toString();
    }

    @PreUpdate
    private void preUpdate() {
        modifyDate = LocalDateTime.now();
    }
}
