package io.brunoonofre64.domain.entities;

import io.brunoonofre64.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TBL_ORDER")
@SQLDelete(sql = "UPDATE TBL_ORDER SET STATUS = 'APPROVED' WHERE UUID = ?", check = ResultCheckStyle.COUNT)
@FilterDef(name = "deleteOrder", parameters = @ParamDef(name = "deleted", type = "Status"))
@Filter(name = "deleteOrder", condition = "Status = :deleted")
@SequenceGenerator(name = "requestSequence", sequenceName = "SQ_request", allocationSize = 1)
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "requestSequence")
    @Column(name = "ID")
    private Long id;

    @Column(name = "UUID", nullable = false, length = 36)
    private String uuid;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false)
    private Status status = Status.APPROVED;

    @Column(name = "ORDER_DATE", nullable = false)
    private LocalDateTime orderDate;

    @Column(name = "TOTAL", nullable = false)
    private BigDecimal total;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    private CustomerEntity customerEntity;

    @OneToMany(mappedBy = "orderEntity")
    private List<OrderItemsEntity> orderItems;

    @PrePersist
    private void prePersist() {
        uuid = java.util.UUID.randomUUID().toString();
    }
}
