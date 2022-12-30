package io.brunoonofre64.domain.entities;

import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "TBL_CUSTOMER")
@SequenceGenerator(name = "sequenceCustomer", sequenceName = "SQ_CUSTOMER", allocationSize = 1)
public class CustomerEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceCustomer")
    @Column(name = "ID")
    private Long id;

    @CPF
    @Column(name = "CPF")
    private String cpf;

    @Column(name = "CUSTOMER_NAME", nullable = false, length = 100)
    private String name;

    @Column(name = "CUSTOMER_AGE", nullable = false, length = 3)
    private String age;

    @OneToMany(mappedBy = "customer")
    private List<OrderEntity> orders;

    public CustomerEntity(String uuid, LocalDateTime inclusionDate, LocalDateTime modifyDate,
                          Long id, String cpf, String name, String age) {
        super(uuid, inclusionDate, modifyDate);
        this.id = id;
        this.cpf = cpf;
        this.name = name;
        this.age = age;
    }
}
