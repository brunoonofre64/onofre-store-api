package io.brunoonofre64.domain.dto.user;

import io.brunoonofre64.domain.enums.Roles;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInputDTO {

    private String login;

    private String password;

    private Roles role;
}
