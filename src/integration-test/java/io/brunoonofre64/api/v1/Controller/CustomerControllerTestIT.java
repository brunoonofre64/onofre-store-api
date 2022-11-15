package io.brunoonofre64.api.v1.Controller;

import io.brunoonofre64.api.v1.utils.ConstantsTest;
import io.brunoonofre64.domain.entities.CustomerEntity;
import io.brunoonofre64.infrastructure.jpa.CustomerRepository;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static io.brunoonofre64.api.v1.utils.ConstantsTest.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("TEST-H2")
@AutoConfigureMockMvc
@AllArgsConstructor
public class CustomerControllerTestIT {
    private CustomerRepository customerRepository;
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        customerRepository.deleteAll();;
    }

    @Test
    @DisplayName("retorna 10 clientes em uma pagina")
    void mustShowTenCustomers() throws Exception{

        CustomerEntity customerEntity = buildCustomerDefault();
        customerEntity.setId(null);

       mockMvc.perform(MockMvcRequestBuilders.get(WEB_METHOD_TEST.V1_CUSTOMER))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andDo(MockMvcResultHandlers.print());
   }



}

