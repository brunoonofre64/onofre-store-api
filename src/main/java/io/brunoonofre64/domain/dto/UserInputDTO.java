package io.brunoonofre64.domain.dto;

import io.brunoonofre64.domain.entities.EmployeeEntity;
import io.brunoonofre64.domain.enums.Roles;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInputDTO {

    private Roles role;

    private EmployeeEntity employee;
}
