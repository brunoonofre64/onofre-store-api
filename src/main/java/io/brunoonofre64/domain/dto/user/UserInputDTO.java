package io.brunoonofre64.domain.dto.user;

import io.brunoonofre64.domain.enums.Profiles;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserInputDTO {
    private String username;
    private String password;
    private List<Profiles> profiles;
}
