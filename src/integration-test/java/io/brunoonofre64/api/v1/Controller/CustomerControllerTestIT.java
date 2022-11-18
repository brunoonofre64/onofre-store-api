package io.brunoonofre64.api.v1.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.brunoonofre64.api.v1.wrapper.PageableResponse;
import io.brunoonofre64.domain.entities.CustomerEntity;
import io.brunoonofre64.domain.exception.DtoNullOrIsEmptyException;
import io.brunoonofre64.infrastructure.jpa.CustomerRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static io.brunoonofre64.api.v1.utils.ConstantsTest.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("TEST-H2")
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
public class CustomerControllerTestIT {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TestRestTemplate testRestTemplate;

    @BeforeEach
    public void setUp() {
        customerRepository.deleteAll();;
    }

    @Test
    @DisplayName("Save new customer em DB, and verify if respnse status is created")
    void mustSaveNewCustomerInDataBase_doneSuccessfuly() throws Exception {
        CustomerEntity customerEntity = customerRepository.save(buildCustomerDefault());

        String customerRequest = objectMapper.writeValueAsString(customerEntity);

        mockMvc.perform(post(WEB_METHOD_TEST.V1_CUSTOMER)
                .contentType(MediaType.APPLICATION_JSON)
                .content(customerRequest))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(TEXT_DEFAULT))
                .andDo(MockMvcResultHandlers.print());

        Assertions.assertThat(customerRequest).isNotEmpty().isNotNull();
    }

    @Test
    @DisplayName("Try to save new customer in BD, with an empty field, then returns bad request")
    void tryToSave_newCustomerInBdWithEmptyField_andReturnBadRequest() throws Exception {
        CustomerEntity customerEntity = customerRepository.save(buildCustomerDefault());
        customerEntity.setName("");

        String customerRequest = objectMapper.writeValueAsString(customerEntity);

        mockMvc.perform(post(WEB_METHOD_TEST.V1_CUSTOMER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerRequest))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException()
                        instanceof DtoNullOrIsEmptyException))
                .andDo(MockMvcResultHandlers.print());

        Assertions.assertThat(customerRequest).isNotEmpty().isNotNull();
    }

    @Test
    @DisplayName("Try to save new customer in BD, with an null field, then returns bad request")
    void tryToSave_newCustomerInBdWithNullField_andReturnBadRequest() throws Exception {
        CustomerEntity customerEntity = customerRepository.save(buildCustomerDefault());
        customerEntity.setName(null);

        String customerRequest = objectMapper.writeValueAsString(customerEntity);

        mockMvc.perform(post(WEB_METHOD_TEST.V1_CUSTOMER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerRequest))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException()
                        instanceof DtoNullOrIsEmptyException))
                .andDo(MockMvcResultHandlers.print());

        Assertions.assertThat(customerRequest).isNotEmpty().isNotNull();
    }

    @Test
    @DisplayName("list returns list of customers inside page object when successful")
    void list_ReturnsListOfCustomersInsidePageObject_WhenSuccessful() throws Exception {
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


