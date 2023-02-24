package br.com.onofrestore.domain.dto.customer;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerOutputDTO {
    private Long id;
    private String uuid;
    private String name;
    private String age;
    private String cpf;
    private LocalDateTime inclusionDate;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDateTime modifyDate;
}
