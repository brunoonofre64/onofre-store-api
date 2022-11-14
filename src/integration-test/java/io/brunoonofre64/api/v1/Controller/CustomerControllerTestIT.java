package io.brunoonofre64.api.v1.Controller;

import io.brunoonofre64.infrastructure.jpa.CustomerRepository;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

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
    void mustShowCustomers() throws Exception{


    }



}
