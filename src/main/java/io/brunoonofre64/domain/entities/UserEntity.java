package io.brunoonofre64.domain.entities;

import io.brunoonofre64.domain.enums.Roles;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TBL_USER")
@SequenceGenerator(name = "sequenceUser", sequenceName = "SQ_USER", allocationSize = 1)
public class UserEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceUser")
    @Column(name = "ID")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE", nullable = false)
    private Roles role;

    @OneToOne
    @JoinColumn(name = "EMPLOYEE_ID")
    private EmployeeEntity employeeEntity;

    public UserEntity(String uuid, LocalDateTime inclusionDate, LocalDateTime modifyDate,
                      Long id, Roles role, EmployeeEntity employeeEntity) {
        super(uuid, inclusionDate, modifyDate);
        this.id = id;
        this.role = role;
        this.employeeEntity = employeeEntity;
    }
}
