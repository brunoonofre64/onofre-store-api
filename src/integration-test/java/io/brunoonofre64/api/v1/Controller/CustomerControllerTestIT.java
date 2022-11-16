package io.brunoonofre64.api.v1.Controller;

import io.brunoonofre64.api.v1.utils.ConstantsTest;
import io.brunoonofre64.api.wrapper.PageableResponse;
import io.brunoonofre64.domain.entities.CustomerEntity;
import io.brunoonofre64.infrastructure.jpa.CustomerRepository;
import lombok.AllArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static io.brunoonofre64.api.v1.utils.ConstantsTest.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("TEST-H2")
@AutoConfigureMockMvc
public class CustomerControllerTestIT {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TestRestTemplate testRestTemplate;

    @BeforeEach
    public void setUp() {
        customerRepository.deleteAll();;
    }

    @Test
    @DisplayName("retorna 10 clientes em uma pagina")
    void mustShowTenCustomers() throws Exception{

        CustomerEntity customerEntity = buildCustomerDefault();
        customerEntity.setId(null);


        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.get(WEB_METHOD_TEST.V1_CUSTOMER))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("pageSize").value(10))
                .andReturn();


    }



}


