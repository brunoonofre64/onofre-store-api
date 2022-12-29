package io.brunoonofre64.api.v1.utils;

import io.brunoonofre64.domain.entities.CustomerEntity;
import io.brunoonofre64.domain.entities.OrderEntity;

import java.util.List;

public interface ConstantsTest {
    Long ID_DEFAULT = 1L;

    String UUID = "{uudi}";

    String CPF_DEFAULT = "861.326.235-07";

    String  UUID_INVALID = "123";

    String TEXT_DEFAULT = "TEST";

    String TEXT_DEFAULT_UPDATE = "TEST UPDATE";

    String AGE_DEFAULT = "50";

    String SLASH = "/";

    interface CUSTOMER_ENTITY{
        Long ID = ID_DEFAULT;
        String NAME = TEXT_DEFAULT;
        String AGE = AGE_DEFAULT;
        String CPF = CPF_DEFAULT;
        List<OrderEntity> REQUESTS_LIST = List.of(new OrderEntity());
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
                .cpf(CUSTOMER_ENTITY.CPF)
                .orders(CUSTOMER_ENTITY.REQUESTS_LIST)
                .build();
    }
}
