package io.brunoonofre64.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;
import java.util.UUID;

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

    @PrePersist
    private void prePersist() {
        inclusionDate = LocalDateTime.now();
        uuid = UUID.randomUUID().toString();
    }

    @PreUpdate
    private void preUpdate() {
        modifyDate = LocalDateTime.now();
    }
}
