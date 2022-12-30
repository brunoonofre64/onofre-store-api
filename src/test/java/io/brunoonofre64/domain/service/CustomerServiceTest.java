package io.brunoonofre64.domain.service;

import io.brunoonofre64.domain.dto.CustomerInputDTO;
import io.brunoonofre64.domain.dto.CustomerOutputDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;

import static io.brunoonofre64.utils.ConstantTest.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class CustomerServiceTest {

    private final static String CPF = "38915839668"; //GERADO ALEATORIAMENTE

    private final static String CPF_2 = "86132623507"; //GERADO ALEATORIAMENTE

    private final static String NAME = TEXT;

    private final static String AGE = TEXT;

    private static final String NAME_2 = TEXT_2;

    private static final String AGE_2 = TEXT_2;

    @Mock
    private CustomerService service;

    @Mock
    private Pageable pageable;

    private CustomerOutputDTO customerOutputDTO;

    private CustomerOutputDTO customerOutputDTOUpdate;

    private CustomerInputDTO createCustomerDTO;

    private CustomerInputDTO createCustomerDTOUpdate;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        startCustomer();
    }

    @Test
    @DisplayName("Must save new customer an then return CustomerDTO intance.")
    void mustSaveNewCustomerAnThenReturnCustomerDtoIntance() {
       when(service.saveNewCustomerInDb(any())).thenReturn(customerOutputDTO);

       CustomerOutputDTO response = service.saveNewCustomerInDb(createCustomerDTO);

        assertNotNull(response);
        assertEquals(CustomerOutputDTO.class, response.getClass());
        assertEquals(NAME, response.getName());
        assertEquals(UUID, response.getUuid());
    }

    @Test
    @DisplayName("When search for a customer by uuid and returns the instance.")
    void whenFindByUuidAnThenReturnCustomerInstance() {
       when(service.getCustomerByUuid(any())).thenReturn(customerOutputDTO);

        CustomerOutputDTO response = service.getCustomerByUuid(UUID);

        assertNotNull(response);
        assertEquals(CustomerOutputDTO.class, response.getClass());
        assertEquals(UUID, response.getUuid());
        assertEquals(NAME, response.getName());
        assertEquals(INC_DATE, response.getInclusionDate());
    }

    @Test
    @DisplayName("Must look for all paged clients and return the instance.")
    void mustFindAllCustomerPagedAnReturnAnInstance() {
       when(service.getAllCustomers(any())).thenReturn(buildCustomerDtoPaged());

        Page<CustomerOutputDTO> reponse = service.getAllCustomers(pageable);

        assertNotNull(reponse);
        assertEquals(PageImpl.class, reponse.getClass());
        assertEquals(1, reponse.getSize());
    }

    @Test
    @DisplayName("Must update data of customer and return an instance.")
    void mustUpdateCustomerAndReturnAnInstance() {
        startCustomerDatatoUpdate();
        when(service.updateCustomerByUuid(any(),any())).thenReturn(customerOutputDTOUpdate);

        CustomerOutputDTO response = service.updateCustomerByUuid(UUID, createCustomerDTOUpdate);

        assertNotNull(response);
        assertEquals(CustomerOutputDTO.class, response.getClass());
        assertEquals(NAME_2, response.getName());
        assertEquals(AGE_2, response.getAge());
        assertEquals(INC_DATE, response.getInclusionDate());
        assertEquals(MODF_DATE, response.getModifyDate());
    }

    @Test
    @DisplayName("Must delete customer of repository by uuid with success.")
    void mustDeleteCustomerOfRepositoryByUuidWithSuccess() {
        doNothing().when(service).deleteCustomerOfDb(any());

        service.deleteCustomerOfDb(UUID);

        verify(service).deleteCustomerOfDb(UUID);
        verify(service, times(1)).deleteCustomerOfDb(UUID);
        verify(service, atLeastOnce()).deleteCustomerOfDb(UUID);
    }

    private Page<CustomerOutputDTO> buildCustomerDtoPaged() {
        List<CustomerOutputDTO> entityList = Collections.singletonList(customerOutputDTO);
        return new PageImpl<>(entityList);
    }
    private void startCustomer() {
        createCustomerDTO = new CustomerInputDTO(NAME, AGE, CPF);
        customerOutputDTO = new CustomerOutputDTO(ID, UUID, NAME, AGE, CPF, INC_DATE, MODF_DATE);
    }

    private void startCustomerDatatoUpdate() {
        customerOutputDTOUpdate = new CustomerOutputDTO(ID, UUID, NAME_2, AGE_2, CPF_2, INC_DATE, MODF_DATE);
        createCustomerDTOUpdate = new CustomerInputDTO(NAME_2, AGE_2, CPF_2);
    }
}
