package br.com.onofrestore.domain.dto.user;

import br.com.onofrestore.domain.enums.Profiles;
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
