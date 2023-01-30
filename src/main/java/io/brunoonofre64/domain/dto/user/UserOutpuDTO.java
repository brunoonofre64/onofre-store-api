package io.brunoonofre64.domain.dto.user;

import io.brunoonofre64.domain.enums.Profiles;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class UserOutpuDTO {
    private String uuid;
    private String username;
    private List<Profiles> profiles;
}
