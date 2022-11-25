package io.brunoonofre64.domain.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class CustomerDTO {
    private String uuid;
    private String name;
    private String age;
    private LocalDateTime inclusionDate;
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
