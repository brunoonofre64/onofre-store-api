package br.com.onofrestore.domain.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
public class UserOutpuDTO {
    private String uuid;
    private String username;
    private String email;
    private String fullName;
    private Set<String> profiles;
}
