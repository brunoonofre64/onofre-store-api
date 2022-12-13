package io.brunoonofre64.domain.entities;

import io.brunoonofre64.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TBL_REQUEST")
@SequenceGenerator(name = "requestSequence", sequenceName = "SQ_request", allocationSize = 1)
public class RequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "requestSequence")
    @Column(name = "ID")
    private Long id;

    @Column(name = "UUID", nullable = false, length = 36)
    private String uuid;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false)
    private Status status;

    @Column(name = "REQUEST_DATE", nullable = false)
    private LocalDateTime requestDate;

    @Column(name = "TOTAL", nullable = false)
    private BigDecimal total;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    private CustomerEntity customerEntity;
}
