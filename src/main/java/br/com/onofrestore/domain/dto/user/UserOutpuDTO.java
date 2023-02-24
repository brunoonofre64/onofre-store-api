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
    private Set<String> profiles;
}
