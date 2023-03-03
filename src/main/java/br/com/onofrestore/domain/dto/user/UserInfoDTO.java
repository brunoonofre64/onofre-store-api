package br.com.onofrestore.domain.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class UserInfoDTO {
    private String email;
    private String fullName;
    private String userUuid;
}
