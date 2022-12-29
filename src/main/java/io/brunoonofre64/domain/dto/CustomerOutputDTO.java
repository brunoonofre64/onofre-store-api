package io.brunoonofre64.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
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
