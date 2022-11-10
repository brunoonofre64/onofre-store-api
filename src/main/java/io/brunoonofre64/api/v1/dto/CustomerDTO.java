package io.brunoonofre64.api.v1.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.PrePersist;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class CustomerDTO {
    private String uuid;
    private String name;
    private LocalDateTime inclusionDate;

    @PrePersist
    private void prePersist() {
        inclusionDate = LocalDateTime.now();
        uuid = UUID.randomUUID().toString();
    }
}
