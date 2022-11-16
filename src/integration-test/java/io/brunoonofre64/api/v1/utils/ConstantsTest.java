package io.brunoonofre64.api.v1.utils;

import io.brunoonofre64.domain.entities.CustomerEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public interface ConstantsTest {

    Long ID_DEFAULT = 1L;
    String TEXT_DEFAULT = "TEST";
    String SLASH = "/";

    interface CUSTOMER_ENTITY{
        Long ID = ID_DEFAULT;
        String NAME = TEXT_DEFAULT;
        String AGE = TEXT_DEFAULT;
    }

    interface WEB_METHOD_TEST {
        String V1_CUSTOMER = "/api/v1/cliente";
    }

    static CustomerEntity buildCustomerDefault() {
        return CustomerEntity
                .builder()
                .id(CUSTOMER_ENTITY.ID)
                .name(CUSTOMER_ENTITY.NAME)
                .age(CUSTOMER_ENTITY.AGE)
                .uuid(UUID.randomUUID().toString())
                .inclusionDate(LocalDateTime.now())
                .build();
    }
}
