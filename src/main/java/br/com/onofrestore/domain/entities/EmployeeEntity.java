package br.com.onofrestore.domain.entities;

import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TBL_EMPLOYEE")
@SequenceGenerator(name = "sequenceEmployee", sequenceName = "SQ_EMPLOYEE", allocationSize = 1)
public class EmployeeEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceEmployee")
    @Column(name = "ID")
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "EMAIL", nullable = false, length = 100)
    private String email;

    @CPF
    @Column(name = "CPF", nullable = false)
    private String cpf;
}
