package io.brunoonofre64.api.v1.controller;

import io.brunoonofre64.domain.dto.CustomerDTO;
import io.brunoonofre64.domain.dto.DataToCreateCustomerDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class CustomerControllerTest {

    private final static String UUID = "8d9af531-1809-4f61-ad96-3e0f39b6e643";

    private final static String NAME = "NAME";

    private final static String AGE = "AGE";

    private static final String NAME_2 = "NAME_2";

    private static final String AGE_2 = "AGE_2";

    private final static LocalDateTime INC_DATE = LocalDateTime.now();

    private final static LocalDateTime MODF_DATE = LocalDateTime.now();

    @Mock
    private CustomerController controller;

    @Mock
    private Pageable pageable;

    private CustomerDTO customerDTO;

    private CustomerDTO customerDTOUpdate;

    private DataToCreateCustomerDTO createCustomerDTO;

    private DataToCreateCustomerDTO createCustomerDTOUpdate;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        startCustomer();
    }

    @Test
    @DisplayName("Must save new customer an then return CustomerDTO intance.")
    void mustSaveNewCustomerAnThenReturnCustomerDtoIntance() {
        when(controller.saveNewCustomerInDb(any())).thenReturn(customerDTO);

        CustomerDTO response = controller.saveNewCustomerInDb(createCustomerDTO);

        assertNotNull(response);
        assertEquals(CustomerDTO.class, response.getClass());
        assertEquals(NAME, response.getName());
        assertEquals(UUID, response.getUuid());
    }

    @Test
    @DisplayName("When search for a customer by uuid and returns the instance.")
    void whenFindByUuidAnThenReturnCustomerInstance() {
        when(controller.getCustomerByUuid(any())).thenReturn(customerDTO);

        CustomerDTO response = controller.getCustomerByUuid(UUID);

        assertNotNull(response);
        assertEquals(CustomerDTO.class, response.getClass());
        assertEquals(UUID, response.getUuid());
        assertEquals(NAME, response.getName());
        assertEquals(INC_DATE, response.getInclusionDate());
    }

    @Test
    @DisplayName("Must look for all paged clients and return the instance.")
    void mustFindAllCustomerPagedAnReturnAnInstance() {
        when(controller.getAllCustomers(any())).thenReturn(buildCustomerDtoPaged());

        Page<CustomerDTO> reponse = controller.getAllCustomers(pageable);

        assertNotNull(reponse);
        assertEquals(PageImpl.class, reponse.getClass());
        assertEquals(1, reponse.getSize());
    }

    @Test
    @DisplayName("Must update data of customer and return an instance.")
    void mustUpdateCustomerAndReturnAnInstance() {
        startCustomerDatatoUpdate();
        when(controller.updateCustomerByUuid(any(),any())).thenReturn(customerDTOUpdate);

        CustomerDTO response = controller.updateCustomerByUuid(UUID, createCustomerDTOUpdate);

        assertNotNull(response);
        assertEquals(CustomerDTO.class, response.getClass());
        assertEquals(NAME_2, response.getName());
        assertEquals(AGE_2, response.getAge());
        assertEquals(INC_DATE, response.getInclusionDate());
        assertEquals(MODF_DATE, response.getModifyDate());
    }

    @Test
    @DisplayName("Must delete customer of repository by uuid with success.")
    void mustDeleteCustomerOfRepositoryByUuidWithSuccess() {
        doNothing().when(controller).deleteCustomerOfDb(any());

        controller.deleteCustomerOfDb(UUID);

        verify(controller).deleteCustomerOfDb(UUID);
        verify(controller, times(1)).deleteCustomerOfDb(UUID);
        verify(controller, atLeastOnce()).deleteCustomerOfDb(UUID);
    }

    private Page<CustomerDTO> buildCustomerDtoPaged() {
        List<CustomerDTO> entityList = Collections.singletonList(customerDTO);
        return new PageImpl<>(entityList);
    }
    private void startCustomer() {
        createCustomerDTO = new DataToCreateCustomerDTO(NAME, AGE);
        customerDTO = new CustomerDTO(UUID, NAME, AGE, INC_DATE, MODF_DATE);
    }

    private void startCustomerDatatoUpdate() {
        customerDTOUpdate = new CustomerDTO(UUID, NAME_2, AGE_2, INC_DATE, MODF_DATE);
        createCustomerDTOUpdate = new DataToCreateCustomerDTO(NAME_2, AGE_2);
    }
}
