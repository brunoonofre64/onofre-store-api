package io.brunoonofre64.domain.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "TBL_CLIENTE")
@SequenceGenerator(name = "sequenceCustomer", sequenceName = "SQ_CUSTOMER", allocationSize = 1)
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceCustomer")
    @Column(name = "ID")
    private Long id;
    @Column(name = "UUID", nullable = false, length = 36)
    private String uuid;

    @Column(name = "CUSTOMER_NAME", nullable = false, length = 100)
    private String name;

    @Column(name = "CUSTOMER_AGE", nullable = false, length = 3)
    private String age;
    @Column(name = "INC_DATE", nullable = false)
    private LocalDateTime inclusionDate;

    @Column(name = "ALT_DATE")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDateTime modifyDate;

    @PrePersist
    private void prePersist() {
        inclusionDate = LocalDateTime.now();
        uuid = UUID.randomUUID().toString();
    }

    @PreUpdate
    private void preUpdate() {
        modifyDate = LocalDateTime.now();
    }
}
