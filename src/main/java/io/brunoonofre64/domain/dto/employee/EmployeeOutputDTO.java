package io.brunoonofre64.domain.dto.employee;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.brunoonofre64.domain.dto.user.UserOutpuDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EmployeeOutputDTO {
    private String name;
    private String uuid;
    private String email;
    private String cpf;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private UserOutpuDTO userInfo;
}
