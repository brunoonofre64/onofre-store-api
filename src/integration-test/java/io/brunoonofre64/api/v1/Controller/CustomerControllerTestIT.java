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
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
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
@AutoConfigureTestDatabase
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
    @DisplayName("list returns list of customers inside page object when successful")
    void list_ReturnsListOfCustomersInsidePageObject_WhenSuccessful(){
        CustomerEntity customerEntity = customerRepository.save(buildCustomerDefault());

        String expectedName = customerEntity.getName();

        PageableResponse<CustomerEntity> customerPage = testRestTemplate.exchange(WEB_METHOD_TEST.V1_CUSTOMER,
                HttpMethod.GET, null,
                new ParameterizedTypeReference<PageableResponse<CustomerEntity>>() {
                }).getBody();

        Assertions.assertThat(customerPage).isNotNull();

        Assertions.assertThat(customerPage.toList())
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(customerPage.toList().get(0).getName()).isEqualTo(expectedName);
    }



}


