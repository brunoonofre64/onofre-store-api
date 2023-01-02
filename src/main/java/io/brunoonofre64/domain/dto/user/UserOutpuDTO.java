package io.brunoonofre64.domain.dto.user;

import io.brunoonofre64.domain.enums.Roles;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserOutpuDTO {

    private String uuid;

    private String login;

    private Roles role;

    private String employeeUuid;
}
