package io.brunoonofre64.api.v1.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.brunoonofre64.api.v1.wrapper.PageableResponse;
import io.brunoonofre64.domain.entities.CustomerEntity;
import io.brunoonofre64.domain.exception.DtoNullOrIsEmptyException;
import io.brunoonofre64.domain.exception.UuidNotFoundOrNullException;
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
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static io.brunoonofre64.api.v1.utils.ConstantsTest.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("TEST-H2")
@AutoConfigureMockMvc
public class CustomerControllerTestIT {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        customerRepository.deleteAll();
    }

    @Test
    @DisplayName("Save new customer em DB, and verify if respnse status is created")
    void mustSaveNewCustomerInDataBase_doneSuccessfuly() throws Exception {
        CustomerEntity customerEntity = customerRepository.save(buildCustomerDefault());
        String customerRequest = objectMapper.writeValueAsString(customerEntity);

        mockMvc.perform(post(WEB_METHOD_TEST.V1_CUSTOMER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerRequest))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(TEXT_DEFAULT))
                .andDo(print());

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
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException()
                        instanceof DtoNullOrIsEmptyException))
                .andDo(print());

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
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException()
                        instanceof DtoNullOrIsEmptyException))
                .andDo(print());

        Assertions.assertThat(customerRequest).isNotEmpty().isNotNull();
    }

    @Test
    @DisplayName("list returns list of customers inside page object when successful")
    void list_ReturnsListOfCustomersInsidePageObject_andReturnOk() throws Exception {
        CustomerEntity customerEntity = customerRepository.save(buildCustomerDefault());
        String customerRequest = objectMapper.writeValueAsString(customerEntity);

        mockMvc.perform(get(WEB_METHOD_TEST.V1_CUSTOMER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerRequest))
                .andExpect(status().isOk())
                .andDo(print());

        Assertions.assertThat(customerRequest).isNotEmpty().isNotNull();
    }

    @Test
    @DisplayName("must show customer by uuid, and return status ok")
    void mustShowCustomerByUud_andReturnOk() throws Exception {
        CustomerEntity customerEntity = customerRepository.save(buildCustomerDefault());
        String customerRequest = objectMapper.writeValueAsString(customerEntity);

        String expectedUuid = customerEntity.getUuid();

        mockMvc.perform(get(WEB_METHOD_TEST.V1_CUSTOMER + "/{uuid}", expectedUuid)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerRequest))
                .andExpect(status().isOk())
                .andDo(print());

        Assertions.assertThat(customerRequest).isNotEmpty().isNotNull();
    }

    @Test
    @DisplayName("try to get customer by uuid nonexistent and return bad request")
    void tryToGetCustomerByUuidInvalid_andReturnBadRequest() throws Exception {
        CustomerEntity customerEntity = customerRepository.save(buildCustomerDefault());
        String customerRequest = objectMapper.writeValueAsString(customerEntity);
        String uuidInvalid = "123";

        mockMvc.perform(get(WEB_METHOD_TEST.V1_CUSTOMER + "/{uuid}", uuidInvalid)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerRequest))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException()
                        instanceof UuidNotFoundOrNullException))
                .andDo(print());

        Assertions.assertThat(customerRequest).isNotEmpty().isNotNull();
    }

    @Test
    @DisplayName("Update new data of customer em DB, and verify if respnse status is no content")
    void mustUpdateNewDataOfCustomerInDataBase_doneSuccessfuly() throws Exception {
        CustomerEntity customerEntity = customerRepository.save(buildCustomerDefault());
        customerEntity.setName(TEXT_DEFAULT_UPDATE);
        String expectedUuid = customerEntity.getUuid();
        String expectedName = TEXT_DEFAULT_UPDATE;


        customerRepository.save(customerEntity);
        String customerRequest = objectMapper.writeValueAsString(customerEntity);

        mockMvc.perform(put(WEB_METHOD_TEST.V1_CUSTOMER + "/{uuid}", expectedUuid)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerRequest))
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$.name").value(expectedName))
                .andDo(print());

        Assertions.assertThat(customerRequest).isNotEmpty().isNotNull();
    }
}


